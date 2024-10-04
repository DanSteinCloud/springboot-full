Feature: Call backend with url

  Scenario: Call backend with uri params
    #Given Country name is "TOGO"
    When The client call the endpoint "/api/country/1"
    Then The response status code is 200
    And The returned value should be:
      | id | countryCode | countryAcronyme | countryName | country_flag |
      |  1 |         228 | TG              | TOGO        | TOG          |
