package com.restApiServices;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.response.Response;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class services {


    public Response response;
    public String findByStatus_endpoint = "https://petstore.swagger.io/v2/pet/findByStatus?";
    String findByStatus_mockServer_endpoint = "/v2/pet/findByStatus?";
    WireMockServer wireMockServer;

    public Response getAPi(HashMap<String, String> params) {
        String iparams = "status=" + params.get("status") + "&name=" + params.get("name");
        return response = (Response) given().log().all().
                when().
                header("Content-type", "application/json").
                get(findByStatus_endpoint + iparams).
                then().
                extract().
                response();
    }

    public Response get_MockServer(HashMap<String, String> params) {
        String iparams = "status=" + params.get("status") + "&name=" + params.get("name");

        configureFor("localhost", 1234);
        stubFor(get(urlEqualTo(findByStatus_mockServer_endpoint + iparams)).willReturn(aResponse().withBodyFile("json/response.json")));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String endpoint = "http://localhost:1234" + findByStatus_mockServer_endpoint;
        return response = given().log().all().
                when().
                header("Content-type", "application/json").
                get(endpoint + iparams).
                then().
                extract().
                response();

    }


}
