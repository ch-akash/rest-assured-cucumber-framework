@crud-tests
Feature: CRUD Tests for restful booker

  Scenario: E2E CRUD Scenario for Restful Booker Apis
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
    And  we retrieve the booking using booking id
    Then API Response should have HTTP Status code 200
    And  getBooking API response should have fields same as create request

    When we prepare request for update booking API
      | firstName        | John          |
      | lastName         | Simpson       |
      | depositPid       | false         |
      | additionalNeeds  | Mineral Water |
      | checkInPlusDays  | 22            |
      | checkoutPlusDays | 27            |
      | totalPrice       | 1000          |
    And  we send request to update booking API
    Then API Response should have HTTP Status code 200

    When we send request to delete booking API
    Then API Response should have HTTP Status code 201

    When we retrieve the booking using booking id
    Then API Response should have HTTP Status code 404