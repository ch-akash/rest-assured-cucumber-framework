@create-booking
Feature: Create new bookings using DataTables

  @list-of-maps
  Scenario: Create a new booking using DataTables as a List of Maps
    Given we have a valid request for create booking with following params
      | firstName | lastName | depositPaid | additionalNeeds | totalPrice | checkInPlusDays | checkoutPlusDays |
      | Sam       | Alton    | true        | Cola            | 500        | 10              | 14               |
    When  we send request to create booking API
    Then API Response should have HTTP Status code 200
    And  create booking API response has valid booking ID

  @datable-of-maps
  Scenario: Create a new booking using DataTables as a Map
    Given we have a valid request for create booking with following params as Map and total price 999
      | firstName        | John    |
      | lastName         | Simpson |
      | depositPaid      | false   |
      | additionalNeeds  | Soda    |
      | checkInPlusDays  | 20      |
      | checkoutPlusDays | 25      |
    When  we send request to create booking API
    Then API Response should have HTTP Status code 200
    And  create booking API response has valid booking ID

  Scenario: Create a new booking using DataTables as a Map and a int argument
    Given we have a valid request for create booking with following params as Map and total price 1000
      | firstName        | John    |
      | lastName         | Simpson |
      | depositPaid      | false   |
      | additionalNeeds  | Soda    |
      | checkInPlusDays  | 20      |
      | checkoutPlusDays | 25      |
    When  we send request to create booking API
    Then API Response should have HTTP Status code 200
    And  create booking API response has valid booking ID

  Scenario Outline: Create a new booking using DataTables as a List of Maps
    Given we have a valid request for create booking with following params
      | firstName | lastName | depositPaid | additionalNeeds | totalPrice   | checkInPlusDays | checkoutPlusDays |
      | Sam       | Alton    | true        | Cola            | <totalPrice> | 10              | 14               |
    When  we send request to create booking API
    Then API Response should have HTTP Status code 200
    And  create booking API response has valid booking ID

    Examples:
      | totalPrice |
      | 499        |
      | 1000       |
      | 5000       |