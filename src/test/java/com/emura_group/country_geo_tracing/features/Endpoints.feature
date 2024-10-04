Feature: Call backend with url

  Scenario: Call backend with uri params
    #Given Country name is "TOGO"
    When The client call the endpoint "/country/1"
    #Then The response status code is 200
    #Then The returned string should be: "TOGO"
