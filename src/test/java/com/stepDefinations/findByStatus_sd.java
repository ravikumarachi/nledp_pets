package com.stepDefinations;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.jayway.jsonpath.JsonPath;

import com.methods.read;
import com.restApiServices.services;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class findByStatus_sd {
    services service = new services();
    Response response;
    WireMockServer wireMockServer;

    @Given("^I triggered findByStatus endpoint with \"([^\"]*)\", \"([^\"]*)\"$")
    public void iTriggeredFindByStatusEndpointWith(String status, String name) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("status", status);
        params.put("name", name);
        response = service.getAPi(params);
    }

    @Then("^I should see response code as (\\d+)$")
    public void iShouldSeeResponseCodeAs(int responseCode) {
        assertEquals(responseCode, response.getStatusCode());
    }

    @And("^I should see number of pets (\\d+)$")
    public void iShouldSeeNumberOfPets(int numberOfPets) {
        JSONArray jsonArray = JsonPath.read(response.getBody().asString(), "$");
        assertEquals(numberOfPets, jsonArray.size());
    }

    @And("^I should see number of pets (\\d+) in json response file \"([^\"]*)\"$")
    public void iShouldSeeNumberOfPetsInJsonResponseFile(int numberOfPets, String jsonFile) throws IOException, ParseException {
        read readFile = new read();
        org.json.simple.JSONArray jsonArray = new org.json.simple.JSONArray();
        jsonArray = readFile.readJsonFile(jsonFile);

        assertEquals(numberOfPets, jsonArray.size());
    }

    @Given("^I triggered findByStatus endpoint with \"([^\"]*)\", \"([^\"]*)\" using mockServer$")
    public void iTriggeredFindByStatusEndpointWithUsingMockServer(String status, String name) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("status", status);
        params.put("name", name);
        wireMockServer = new WireMockServer(1234);
        wireMockServer.start();
        response = service.get_MockServer(params);
        wireMockServer.stop();
    }


}
