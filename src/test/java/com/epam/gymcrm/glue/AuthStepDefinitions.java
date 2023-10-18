package com.epam.gymcrm.glue;

import com.epam.gymcrm.services.UserService;
import com.epam.gymcrm.utils.JWTUtil;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.junit.Assert;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Security;
import java.util.logging.Logger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
//@ActiveProfiles("test")
//@ComponentScan(basePackages = {"com.epam.gymcrm"})
public class AuthStepDefinitions {
    private static final Logger logger = Logger.getLogger(AuthStepDefinitions.class.getName());

    private final UserService userService;
    private final JWTUtil jwtUtil;
    @LocalServerPort
    private String port;
    private final String baseUrl = "http://localhost:8080";

    RequestSpecification changeLogin;
    Response responseLogin;
    Response responseChange;
    String newPasswordChange;
    String userLogin;
    String passLogin;

    public AuthStepDefinitions(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Given("valid username {string} and password {string}")
    public void clientEntersValidUsernameAndPassword(String username, String password){
        logger.info("credentials:" + username + password);
        userLogin = username;
        passLogin = password;
    }

    @When("the client calls endpoint to auth {string}")
    public void clientCallsEndpoint(String url){
        responseLogin = RestAssured
                .given()
                .queryParam("username", userLogin)
                .queryParam("password", passLogin)
                .when()
                .get(baseUrl + url);

    }

    @Then("the client receives token")
    public void clientReceivesToken(){
//        UserDetails userDetails = userService.loadUserByUsername(userLogin);
        logger.info("token:" + responseLogin.asString());
        Assert.assertNotNull(responseLogin.asString());
        Assert.assertTrue(jwtUtil.validateToken(responseLogin.asString()));
    }

    @And("the client receives status code of {int} for successful login")
    public void clientReceivesStatusCodeForLogin(int code){
        logger.info("code login:" + code);
        Assert.assertEquals(code, responseLogin.getStatusCode());
    }

    @Given("valid username {string}, current password {string} and new password {string}")
    public void clientEntersValidUsernamePasswordAndNewPassword(String username, String password, String newPassword){
        changeLogin = RestAssured
                .given()
                .queryParam("username", username)
                .queryParam("oldPassword", password)
                .queryParam("newPassword", newPassword);
    }

    @When("the client calls endpoint to change login {string}")
    public void clientCallsEndpointToChangeLogin(String url){
        responseChange = changeLogin
                .when()
                .put(baseUrl + url);
    }

    @Then("the client receives message {string}")
    public void clientReceivesMessage(String message){
        Assert.assertEquals("Successfully updated!", message);
    }

    @And("the client receives status code of {int} for successful change of login")
    public void clientReceivesStatusCodeForChange(int code){
        Assert.assertEquals(code, responseChange.getStatusCode());
    }
}
