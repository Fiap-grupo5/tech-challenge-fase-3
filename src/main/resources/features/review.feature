Feature: Manage Reviews

  Scenario: Create a new review
    Given a valid review
    When I send a request to create the review
    Then the review is successfully created

  Scenario: Fetch a review by ID
    Given an existing review
    When I fetch the review by its ID
    Then the review details are returned successfully

  Scenario: Fetch reviews by restaurant ID
    Given reviews associated with a restaurant
    When I fetch reviews by the restaurant ID
    Then the reviews are returned successfully

  Scenario: Update a review
    Given an existing review
    When I update the review details
    Then the review is successfully updated

  Scenario: Delete a review
    Given an existing review
    When I delete the review
    Then the review is successfully removed
