@simple-get-api @feature-level-tag
Feature: Simple HTTP GET Call

  This is a simple example for HTTP GET with Cucumber.

  @scenario-tag @get-all-booking
  Scenario: Make HTTP GET Call and validate status code is 200
    Given I prepare a simple HTTP GET request
    When I send the request to API
    Then API Response should have HTTP Status code 200