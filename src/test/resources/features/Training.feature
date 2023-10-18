Feature: Training management
  @SpringBean
  Scenario: User adds training
    Given a valid username "Christian.Vargas4", valid password "PYH41N?VQ@"
    And a valid trainer username "Eduardo.Raudales13", training name "Training", training date "2000-02-28"
    And a training duration 25, training type "Fitness"
    When the client calls endpoint "/api/training/add" to add new training
    Then the client receives message "Training added successfully!" after training is added
    And the client receives status code of 200 for successful addition of Training
