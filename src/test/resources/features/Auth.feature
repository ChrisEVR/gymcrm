Feature: Login functionality

  @SpringBean
  Scenario: User logs in with valid credentials
    Given valid username "Christian.Vargas4" and password "PYH41N?VQ@"
    When the client calls endpoint to auth "/api/auth/login"
    Then the client receives token
    And the client receives status code of 200 for successful login

  @SpringBean
  Scenario: User changes credentials
    Given valid username "Christian.Vargas4", current password "PYH41N?VQ@" and new password "PYH41N?VQ@"
    When the client calls endpoint to change login "/api/auth/change"
    Then the client receives message "Successfully updated!"
    And the client receives status code of 200 for successful change of login