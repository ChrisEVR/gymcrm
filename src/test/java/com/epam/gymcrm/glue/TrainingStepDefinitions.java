package com.epam.gymcrm.glue;

import com.epam.gymcrm.controllers.TraineeController;
import com.epam.gymcrm.controllers.TrainerController;
import com.epam.gymcrm.controllers.TrainingTypeController;
import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.TrainingType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

public class TrainingStepDefinitions {
    private static final Logger logger = Logger.getLogger(TrainingTypeStepDefinitions.class.getName());
    private final String baseUrl = "http://localhost:8080";
    private Response trainingTypes;
    private Response responseTraining;
    private Training training;
    private String trainingDate;
    private String trainingTrainingType;
    private String trainingName;
    private Long trainingDuration;
    private String trainerUsername;
    private String traineeUsername;
    private String token;
    private RequestSpecification requestSpecification;

    @Given("a valid username {string}, valid password {string}")
    public void validUsernameAndPassword(String username, String password){
        Response responseLogin = RestAssured
                .given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(baseUrl + "/api/auth/login");

        token = "Bearer " + responseLogin.asString();

        this.traineeUsername = username;

        logger.info("token:" + token);
        logger.info("trainee:" + this.traineeUsername);
    }

    @And("a valid trainer username {string}, training name {string}, training date {string}")
    public void validTrainerUsernameAndTrainingNameAndTrainingDate(
            String trainerUsername,
            String trainingName,
            String date
    ) throws ParseException {
        this.trainingDate = date;
        this.trainerUsername = trainerUsername;
        this.trainingName = trainingName;

        logger.info("trainer:" + trainerUsername);
    }

    @And("a training duration {int}, training type {string}")
    public void validTrainingDuration(Integer duration, String trainingType){
        this.trainingDuration = (long) duration;
        this.trainingTrainingType = trainingType;
    }

    @When("the client calls endpoint {string} to add new training")
    public void clientGetTraining(String url){
        logger.info("url:" + baseUrl + url);
        responseTraining = RestAssured
                .given()
                .header("Authorization",  token)
                .queryParam("traineeUsername", traineeUsername)
                .queryParam("trainerUsername", trainerUsername)
                .queryParam("trainingName", trainingName)
                .queryParam("trainingDate", trainingDate)
                .queryParam("trainingDuration", trainingDuration)
                .queryParam("trainingType", trainingTrainingType)
                .when()
                .post(baseUrl + url);
        logger.info("responseTraining:" + responseTraining.asString());
    }

    @Then("the client receives message {string} after training is added")
    public void verifyReceivedMessage(String message) throws JsonProcessingException {
        Assert.assertEquals(message, responseTraining.asString());
    }

    @And("the client receives status code of {int} for successful addition of Training")
    public void verifyTrainingResponseCode(int code){
        Assert.assertEquals(code, responseTraining.getStatusCode());
    }

}
