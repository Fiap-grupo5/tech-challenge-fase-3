package com.g5.restaurants.aplication.infrastructure.api;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDateTime;
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
class ReviewControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private MongoTemplate mongoTemplate;

    private String restaurantId;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Limpa o banco de dados antes de cada teste
        mongoTemplate.getDb().drop();

        // Cria um restaurante necessário para os testes
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

        System.out.println("Restaurant ID being used: " + restaurantId);
    }

    @Test
    void shouldCreateReviewSuccessfully() {
        var reviewRequest = new HashMap<String, Object>();
        reviewRequest.put("restaurantId", restaurantId);
        reviewRequest.put("reviewerName", "Gabriel Silva");
        reviewRequest.put("rating", 5);
        reviewRequest.put("comments", "Ótimo restaurante!");

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(reviewRequest)
        .when()
            .post("/review")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("reviewerName", equalTo("Gabriel Silva"))
            .body("rating", equalTo(5))
            .body("comments", equalTo("Ótimo restaurante!"));
    }

    @Test
    void shouldFetchReviewById() {
        var id = UUID.randomUUID().toString();

        // Insere um review no banco diretamente
        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("restaurantId", restaurantId);
            put("reviewerName", "Gabriel Silva");
            put("rating", 4);
            put("comments", "Ótimo restaurante!");
            put("createdAt", LocalDateTime.now());
            put("updatedAt", LocalDateTime.now());
        }}, "review");

        given()
            .pathParam("id", id)
        .when()
            .get("/review/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(id))
            .body("reviewerName", equalTo("Gabriel Silva"));
    }

    @Test
    void shouldUpdateReviewSuccessfully() {
        var id = UUID.randomUUID().toString();

        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("restaurantId", restaurantId);
            put("reviewerName", "Gabriel Silva");
            put("rating", 4);
            put("comments", "Ótimo restaurante!");
            put("createdAt", LocalDateTime.now());
            put("updatedAt", LocalDateTime.now());
        }}, "review");

        var updateRequest = new HashMap<String, Object>();
        updateRequest.put("reviewerName", "João Pedro");
        updateRequest.put("rating", 5);
        updateRequest.put("comments", "Melhor ainda!");

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .pathParam("id", id)
            .body(updateRequest)
        .when()
            .put("/review/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("reviewerName", equalTo("João Pedro"))
            .body("rating", equalTo(5));
    }

    @Test
    void shouldDeleteReviewSuccessfully() {
        var id = UUID.randomUUID().toString();

        mongoTemplate.insert(new HashMap<String, Object>() {{
            put("_id", id);
            put("restaurantId", restaurantId);
            put("reviewerName", "Gabriel Silva");
            put("rating", 4);
            put("comments", "Ótimo restaurante!");
        }}, "review");

        given()
            .pathParam("id", id)
        .when()
            .delete("/review/{id}")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

        var deletedReview = mongoTemplate.findById(id, HashMap.class, "review");
        assertThat(deletedReview).isNull();
    }

    @Test
    void shouldFetchReviewsByRestaurantId() {
        var review1 = new HashMap<String, Object>() {{
            put("_id", UUID.randomUUID().toString()); // UUID válido para o _id
            put("restaurantId", restaurantId);        // Já é um UUID válido gerado no setup
            put("reviewerName", "João");
            put("rating", 3);
            put("comments", "Bom.");
            put("createdAt", LocalDateTime.now());
            put("updatedAt", LocalDateTime.now());
        }};

        var review2 = new HashMap<String, Object>() {{
            put("_id", UUID.randomUUID().toString()); 
            put("restaurantId", restaurantId);        
            put("reviewerName", "Maria");
            put("rating", 5);
            put("comments", "Excelente!");
            put("createdAt", LocalDateTime.now());
            put("updatedAt", LocalDateTime.now());
        }};

        mongoTemplate.insert(review1, "review");
        mongoTemplate.insert(review2, "review");

        given()
            .pathParam("restaurantId", restaurantId)
        .when()
            .get("/review/restaurant/{restaurantId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("[0].reviewerName", equalTo("João"))
            .body("[1].reviewerName", equalTo("Maria"));
    }
}
    