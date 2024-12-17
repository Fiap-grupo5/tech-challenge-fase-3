package com.g5.restaurants.aplication.infrastructure.api;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private MongoTemplate mongoTemplate;

    private String restaurantId;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        mongoTemplate.getDb().drop();

        var restaurantRequest = new HashMap<String, Object>();
        restaurantRequest.put("name", "Tia Nicole");
        restaurantRequest.put("numberOfTables", 10);
        restaurantRequest.put("address", "Rua das Clarisas, 100");
        restaurantRequest.put("city", "Belo Horizonte");
        restaurantRequest.put("state", "MG");
        restaurantRequest.put("type", "BRAZILIAN");
        restaurantRequest.put("openedAt", "10:00:00");
        restaurantRequest.put("closedAt", "22:00:00");

        restaurantId = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(restaurantRequest)
        .when()
            .post("/restaurant")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .path("id");
    }

    @Test
    void shouldCreateReservationSuccessfully() {
        var request = new HashMap<String, Object>();
        request.put("restaurantId", restaurantId);
        request.put("customerName", "Gabriel Silva");
        request.put("customerContact", "(11) 98765-4321");
        request.put("reservationDate", LocalDate.now().plusDays(1).toString());
        request.put("numberOfTables", 2);
        request.put("status", "PENDING");

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
        .when()
            .post("/reservation")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("customerName", equalTo("Gabriel Silva"))
            .body("customerContact", equalTo("(11) 98765-4321"))
            .body("status", equalTo("PENDING"));
    }


    @Test
    void shouldFetchReservationById() {
        var id = UUID.randomUUID().toString();

        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("restaurantId", restaurantId);
            put("customerName", "Gabriel Silva");
            put("customerContact", "(11) 98765-4321");
            put("reservationDate", LocalDate.now().toString());
            put("numberOfTables", 2);
            put("status", "PENDING");
        }}, "reservation");

        given()
            .pathParam("id", id)
        .when()
            .get("/reservation/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(id))
            .body("customerName", equalTo("Gabriel Silva"));
    }

    @Test
    void shouldUpdateReservationSuccessfully() {
        var id = UUID.randomUUID().toString();

        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("restaurantId", restaurantId);
            put("customerName", "Gabriel Silva");
            put("customerContact", "(11) 98765-4321");
            put("reservationDate", LocalDate.now().toString());
            put("numberOfTables", 2);
            put("status", "PENDING");
        }}, "reservation");

        var updateRequest = new HashMap<String, Object>();
        updateRequest.put("status", "CONFIRMED");

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .pathParam("id", id)
            .body(updateRequest)
        .when()
            .put("/reservation/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("status", equalTo("CONFIRMED"));
    }

    @Test
    void shouldDeleteReservationSuccessfully() {
        var id = UUID.randomUUID().toString();

        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("restaurantId", restaurantId);
            put("customerName", "Gabriel Silva");
            put("customerContact", "(11) 98765-4321");
            put("reservationDate", LocalDate.now().toString());
            put("numberOfTables", 2);
            put("status", "PENDING");
        }}, "reservation");

        given()
            .pathParam("id", id)
        .when()
            .delete("/reservation/{id}")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

        var deletedReservation = mongoTemplate.findById(id, HashMap.class, "reservation");
        assertThat(deletedReservation).isNull();
    }

}
