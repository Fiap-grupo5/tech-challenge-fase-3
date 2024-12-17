Feature: Manage Reservations

  Scenario: Create a new reservation
    Given a valid reservation
    When I send a request to create the reservation
    Then the reservation is successfully created

  Scenario: Fetch a reservation by ID
    Given an existing reservation
    When I fetch the reservation by its ID
    Then the reservation details are returned successfully

  Scenario: Update a reservation
    Given an existing reservation
    When I update the reservation status
    Then the reservation is successfully updated

  Scenario: Delete a reservation
    Given an existing reservation
    When I delete the reservation
    Then the reservation is successfully removed
