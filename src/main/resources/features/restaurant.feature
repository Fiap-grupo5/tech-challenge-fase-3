Feature: Manage Restaurants

  Scenario: Create a new restaurant
    Given a valid restaurant
    When I send a request to create the restaurant
    Then the restaurant is successfully created

  Scenario: Fetch a restaurant by ID
    Given an existing restaurant
    When I fetch the restaurant by its ID
    Then the restaurant details are returned successfully

  Scenario: Update a restaurant
    Given an existing restaurant
    When I update the restaurant details
    Then the restaurant is successfully updated

  Scenario: Delete a restaurant
    Given an existing restaurant
    When I delete the restaurant
    Then the restaurant is successfully removed
