@delete-booking
Feature: Delete an existing booking

  Scenario: Create and delete a booking
    Given we have a valid request for create booking with following params as Map and total price 999
      | firstName        | John    |
      | lastName         | Simpson |
      | depositPid       | false   |
      | additionalNeeds  | Soda    |
      | checkInPlusDays  | 20      |
      | checkoutPlusDays | 25      |
    And  we send request to create booking API
    Then API Response should have HTTP Status code 200
    And  create booking API response has valid booking ID

    When booking id has been saved in shared context
    And  we send request to delete booking API

    Then API Response should have HTTP Status code 201