package com.g5.restaurants.aplication.bdd;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ReservationStepDefinitions {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    private Response response;
    private String baseUrl = "https://localhost:8080";
    private String reservationId;

    @Given("a valid reservation")
    public void a_valid_reservation() {
        String requestBody = """
        {
          "restaurantId": "01ec2160-587e-4551-bc4a-3b65484058f8",
          "customerName": "Gabriel Silva",
          "customerContact": "123456789",
          "reservationDate": "2024-01-01",
          "numberOfTables": 2,
          "status": "PENDING"
        }
        """;
        response = given().contentType("application/json")
                .body(requestBody)
                .when().post(baseUrl + "/reservation");

        response.then().statusCode(200);
        reservationId = response.jsonPath().getString("id");
    }

    @When("I fetch the reservation by its ID")
    public void i_fetch_the_reservation_by_its_id() {
        response = given().when().get(baseUrl + "/reservation/" + reservationId);
    }

    @Then("the reservation details are returned successfully")
    public void the_reservation_details_are_returned_successfully() {
        response.then().statusCode(200)
                .body("id", equalTo(reservationId));
    }

    @When("I update the reservation status")
    public void i_update_the_reservation_status() {
        String updateBody = """
        {
          "status": "CONFIRMED"
        }
        """;
        response = given().contentType("application/json")
                .body(updateBody)
                .when().put(baseUrl + "/reservation/" + reservationId);
    }

    @Then("the reservation is successfully updated")
    public void the_reservation_is_successfully_updated() {
        response.then().statusCode(200)
                .body("status", equalTo("CONFIRMED"));
    }

    @When("I delete the reservation")
    public void i_delete_the_reservation() {
        response = given().when().delete(baseUrl + "/reservation/" + reservationId);
    }

    @Then("the reservation is successfully removed")
    public void the_reservation_is_successfully_removed() {
        response.then().statusCode(204);
    }

    @When("I send a request to create the reservation")
    public void i_send_a_request_to_create_the_reservation() {
        String requestBody = """
        {
          "restaurantId": "01ec2160-587e-4551-bc4a-3b65484058f8",
          "customerName": "Gabriel Silva",
          "customerContact": "123456789",
          "reservationDate": "2024-01-01",
          "numberOfTables": 2,
          "status": "PENDING"
        }
        """;
        response = given().contentType("application/json")
                .body(requestBody)
                .when().post(baseUrl + "/reservation");
    }
    
    @Then("the reservation is successfully created")
    public void the_reservation_is_successfully_created() {
        response.then().statusCode(200)
                .body("status", equalTo("PENDING"));
    }

    @Given("an existing reservation")
    public void an_existing_reservation() {
        // Aqui você pode buscar uma reserva já criada ou garantir que uma reserva está criada previamente
        String requestBody = """
        {
          "restaurantId": "01ec2160-587e-4551-bc4a-3b65484058f8",
          "customerName": "João Silva",
          "customerContact": "987654321",
          "reservationDate": "2024-01-02",
          "numberOfTables": 1,
          "status": "PENDING"
        }
        """;
        response = given().contentType("application/json")
                .body(requestBody)
                .when().post(baseUrl + "/reservation");
    
        response.then().statusCode(200);
        reservationId = response.jsonPath().getString("id");
    }
    
}
