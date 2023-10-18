Feature: Training type test

  @SpringBean
  Scenario: Client makes call to GET /api/training-type
    Given a valid username "Eduardo.Raudales4" and a valid password "newPassword"
    When the client calls endpoint "/api/training-type" to get all training types
    Then the client receives all training types
    And the client receives status code of 200