package com.epam.gymcrm.glue;

import com.epam.gymcrm.controllers.TrainingTypeController;
import com.epam.gymcrm.models.TrainingType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.Header;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.logging.Logger;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@CucumberContextConfiguration
//@ActiveProfiles("test")
//@SpringBootTest
public class TrainingTypeStepDefinitions {
    private static final Logger logger = Logger.getLogger(TrainingTypeStepDefinitions.class.getName());
    @LocalServerPort
    String port;
    String baseUrl = "http://localhost:8080";
    Response responseLogin;
    Response trainingTypes;
    String token;
    RequestSpecification requestSpecification;

    @Given("a valid username {string} and a valid password {string}")
    public void validUsernameAndPassword(String username, String password){

        responseLogin = RestAssured
                .given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(baseUrl + "/api/auth/login");

        token = "Bearer " + responseLogin.asString();
        logger.info("token:" + token);
    }

    @When("the client calls endpoint {string} to get all training types")
    public void clientGetsTrainingTypes(String url){
        logger.info("url:" + baseUrl + url);
        trainingTypes = RestAssured
                .given()
                .header("Authorization",  token)
                .when()
                .get(baseUrl + url);
    }

    @Then("the client receives all training types")
    public void verifyTrainingTypesList() throws JsonProcessingException {
        logger.info("List:" + trainingTypes.asString());

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType entityType = objectMapper
                .getTypeFactory()
                .constructCollectionType(List.class, TrainingType.class);

        List<TrainingType> entityList = objectMapper.readValue(trainingTypes.asString(), entityType);

        Assert.assertEquals(5, entityList.size());
        Assert.assertNotNull(trainingTypes.getBody());
    }

    @And("the client receives status code of {int}")
    public void verifyTrainingTypeResponseCode(int code){
        Assert.assertEquals(code, trainingTypes.getStatusCode());
    }
}
