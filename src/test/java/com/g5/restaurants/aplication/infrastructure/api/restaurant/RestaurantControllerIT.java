package com.g5.restaurants.aplication.infrastructure.api.restaurant;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.TimeZone;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
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
class RestaurantControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeAll
    static void setupTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); 
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        mongoTemplate.getDb().drop();
    }

    @Test
    void shouldCreateRestaurantSuccessfully() {
        var request = new HashMap<String, Object>();
        request.put("name", "Tia Nicole");
        request.put("numberOfTables", 10);
        request.put("address", "Rua das Clarisas, 100");
        request.put("city", "Belo Horizonte");
        request.put("state", "MG");
        request.put("type", "BRAZILIAN");
        request.put("openedAt", "10:00:00");
        request.put("closedAt", "22:00:00");

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
        .when()
            .post("/restaurant")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("name", equalTo("Tia Nicole"))
            .body("numberOfTables", equalTo(10));
    }


    @Test
    void shouldFetchRestaurantById() {
        var id = UUID.randomUUID().toString();
        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("name", "Tia Nicole");
            put("numberOfTables", 10);
            put("address", "Rua das Clarisas, 100");
            put("city", "Belo Horizonte");
            put("state", "MG");
            put("type", "BRAZILIAN");
            put("openedAt", "10:00:00");
            put("closedAt", "22:00:00");
        }}, "restaurant");

        given()
            .pathParam("id", id)
        .when()
            .get("/restaurant/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(id))
            .body("name", equalTo("Tia Nicole"));
    }

    @Test
    void shouldUpdateRestaurantSuccessfully() {
        var id = UUID.randomUUID().toString();
        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("name", "Tia Nicole");
            put("numberOfTables", 10);
            put("address", "Rua das Clarisas, 100");
            put("city", "Belo Horizonte");
            put("state", "MG");
            put("type", "BRAZILIAN");
            put("openedAt", "10:00:00");
            put("closedAt", "22:00:00");
        }}, "restaurant");

        var updateRequest = new HashMap<String, Object>();
        updateRequest.put("name", "Tia Maria");
        updateRequest.put("numberOfTables", 15);
        updateRequest.put("address", "Rua das Oliveiras, 200");
        updateRequest.put("city", "Sao Paulo");
        updateRequest.put("state", "SP");
        updateRequest.put("type", "ITALIAN");
        updateRequest.put("openedAt", "11:00:00");
        updateRequest.put("closedAt", "23:00:00");

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .pathParam("id", id)
            .body(updateRequest)
        .when()
            .put("/restaurant/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo("Tia Maria"))
            .body("numberOfTables", equalTo(15));
    }

    @Test
    void shouldDeleteRestaurantSuccessfully() {
        var id = UUID.randomUUID().toString();
        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("name", "Tia Nicole");
            put("numberOfTables", 10);
        }}, "restaurant");

        given()
            .pathParam("id", id)
        .when()
            .delete("/restaurant/{id}")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

        var deletedRestaurant = mongoTemplate.findById(id, HashMap.class, "restaurant");
        assertThat(deletedRestaurant).isNull();
    }

    @Test
    void shouldStoreAndFetchRestaurantWithLocalTimeSuccessfully() {
        var request = new HashMap<String, Object>();
        request.put("name", "Tia Nicole");
        request.put("numberOfTables", 10);
        request.put("address", "Rua das Clarisas, 100");
        request.put("city", "Belo Horizonte");
        request.put("state", "MG");
        request.put("type", "BRAZILIAN");
        request.put("openedAt", "10:00:00");
        request.put("closedAt", "22:00:00");
    
        var id = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
        .when()
            .post("/restaurant")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .path("id");
    
        given()
            .pathParam("id", id)
        .when()
            .get("/restaurant/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("openedAt", equalTo("10:00:00"))
            .body("closedAt", equalTo("22:00:00"));
    }
}
