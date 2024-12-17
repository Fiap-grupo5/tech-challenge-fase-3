package com.g5.restaurants.aplication.bdd;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestaurantStepDefinitions {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    private Response response;
    private String baseUrl = "https://localhost:8080";
    private String restaurantId;

    // Step to create a valid restaurant
    @Given("a valid restaurant")
    public void a_valid_restaurant() {
        String requestBody = """
        {
          "name": "Tia Nicole",
          "numberOfTables": 10,
          "address": "Rua das Clarisas, 100",
          "city": "Belo Horizonte",
          "state": "MG",
          "type": "BRAZILIAN",
          "openedAt": "10:00:00",
          "closedAt": "22:00:00"
        }
        """;
        response = given().contentType("application/json")
                .body(requestBody)
                .when().post(baseUrl + "/restaurant");

        response.then().statusCode(201);
        restaurantId = response.jsonPath().getString("id");
        Assertions.assertNotNull(restaurantId);
    }

    @When("I fetch the restaurant by its ID")
    public void i_fetch_the_restaurant_by_its_id() {
        response = given().when().get(baseUrl + "/restaurant/" + restaurantId);
    }

    @Then("the restaurant details are returned successfully")
    public void the_restaurant_details_are_returned_successfully() {
        response.then().statusCode(200)
                .body("id", equalTo(restaurantId));
    }

    @When("I update the restaurant details")
    public void i_update_the_restaurant_details() {
        String updateBody = """
        {
          "name": "Tia Nicole Updated",
          "numberOfTables": 12,
          "address": "Rua Nova, 200",
          "city": "São Paulo",
          "state": "SP",
          "type": "ITALIAN",
          "openedAt": "09:00:00",
          "closedAt": "21:00:00"
        }
        """;
        response = given().contentType("application/json")
                .body(updateBody)
                .when().put(baseUrl + "/restaurant/" + restaurantId);
    }

    @Then("the restaurant is successfully updated")
    public void the_restaurant_is_successfully_updated() {
        response.then().statusCode(200)
                .body("name", equalTo("Tia Nicole Updated"));
    }

    @When("I delete the restaurant")
    public void i_delete_the_restaurant() {
        response = given().when().delete(baseUrl + "/restaurant/" + restaurantId);
    }

    @Then("the restaurant is successfully removed")
    public void the_restaurant_is_successfully_removed() {
        response.then().statusCode(204);
    }

    @Given("an existing restaurant")
    public void an_existing_restaurant() {
        a_valid_restaurant();
    }

    @When("I send a request to create the restaurant")
    public void i_send_a_request_to_create_the_restaurant() {
        String requestBody = """
        {
          "name": "Tia Nicole",
          "numberOfTables": 10,
          "address": "Rua das Clarisas, 100",
          "city": "Belo Horizonte",
          "state": "MG",
          "type": "BRAZILIAN",
          "openedAt": "10:00:00",
          "closedAt": "22:00:00"
        }
        """;
        response = given().contentType("application/json")
                .body(requestBody)
                .when().post(baseUrl + "/restaurant");
    
        response.then().statusCode(201);
    }

    @Then("the restaurant is successfully created")
    public void the_restaurant_is_successfully_created() {
        response.then().statusCode(201);
    
        Assertions.assertNotNull(response.jsonPath().getString("id"), "The restaurant id should not be null");
    }
}
