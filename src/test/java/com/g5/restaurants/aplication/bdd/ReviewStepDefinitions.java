package com.g5.restaurants.aplication.bdd;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Assertions;

public class ReviewStepDefinitions {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    private Response response;
    private String baseUrl = "https://localhost:8080";
    private String reviewId;


    @Given("a valid review")
    public void a_valid_review() {
        String requestBody = """
        {
          "restaurantId": "01ec2160-587e-4551-bc4a-3b65484058f8",
          "reviewerName": "João Silva",
          "rating": 85,
          "comments": "Excellent food!"
        }
        """;
        response = given().contentType("application/json")
                .body(requestBody)
                .when().post(baseUrl + "/review");

        response.then().statusCode(200);
        reviewId = response.jsonPath().getString("id");
        Assertions.assertNotNull(reviewId);
    }


    @When("I fetch the review by its ID")
    public void i_fetch_the_review_by_its_id() {
        response = given().when().get(baseUrl + "/review/" + reviewId);
    }


    @Then("the review details are returned successfully")
    public void the_review_details_are_returned_successfully() {
        response.then().statusCode(200)
                .body("id", equalTo(reviewId));
    }

    @When("I update the review details")
    public void i_update_the_review_details() {
        String updateBody = """
        {
          "reviewerName": "Maria Silva",
          "rating": 90,
          "comments": "Even better experience!"
        }
        """;
        response = given().contentType("application/json")
                .body(updateBody)
                .when().put(baseUrl + "/review/" + reviewId);
    }

    @Then("the review is successfully updated")
    public void the_review_is_successfully_updated() {
        response.then().statusCode(200)
                .body("rating", equalTo(90));
    }

    @When("I delete the review")
    public void i_delete_the_review() {
        response = given().when().delete(baseUrl + "/review/" + reviewId);
    }

    @Then("the review is successfully removed")
    public void the_review_is_successfully_removed() {
        response.then().statusCode(204);
    }

    @Given("an existing review")
    public void an_existing_review() {
        a_valid_review();
    }


    @Given("reviews associated with a restaurant")
    public void reviews_associated_with_a_restaurant() {
        a_valid_review();
    }

    @When("I send a request to create the review")
    public void i_send_a_request_to_create_the_review() {
        String requestBody = """
        {
          "restaurantId": "01ec2160-587e-4551-bc4a-3b65484058f8",
          "reviewerName": "João Silva",
          "rating": 85,
          "comments": "Excellent food!"
        }
        """;
        response = given().contentType("application/json")
                .body(requestBody)
                .when().post(baseUrl + "/review");
    }

    @Then("the review is successfully created")
    public void the_review_is_successfully_created() {
        response.then().statusCode(200)
                .body("reviewerName", equalTo("João Silva"))
                .body("rating", equalTo(85));
    }

    @When("I fetch reviews by the restaurant ID")
    public void i_fetch_reviews_by_the_restaurant_id() {
        response = given().when().get(baseUrl + "/review?restaurantId=" + "01ec2160-587e-4551-bc4a-3b65484058f8");
    }
    
    @Then("the reviews are returned successfully")
    public void the_reviews_are_returned_successfully() {
        response.then().statusCode(200)
                .body("restaurantId", equalTo("01ec2160-587e-4551-bc4a-3b65484058f8"));
    }
        
}
