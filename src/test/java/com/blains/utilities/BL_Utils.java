package com.blains.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BL_Utils {

    public static String generateToken(String email, String password) {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("email", email)
                .and()
                .queryParam("password", password)
                .when().get(Environment.URL + "/sign");

        String token = "Bearer " + response.path("accessToken");

        return token;
    }

    //USER1 , team-leader , team-member
    public static String generateTokenByRole(String role) {

        //returnCredentials(role);


        String token = given()
                .queryParams(returnCredentials(role))
                .when().get(Environment.BASE_URL + "/sign")
                .then().statusCode(200).extract().response().path("accessToken");


        return "Bearer " + token;

    }

    public static Map<String, String> returnCredentials(String role) {
        String email = "";
        String password = "";

        switch (role) {
            case "USER1":
                email = Environment.USER1_EMAIL;
                password = Environment.USER1_PASSWORD;
                break;

            case "team-member":
                email = Environment.MEMBER_EMAIL;
                password = Environment.MEMBER_PASSWORD;
                break;
            case "team-leader":
                email = Environment.LEADER_EMAIL;
                password = Environment.LEADER_PASSWORD;
                break;
            default:

                throw new RuntimeException("Invalid Role Entry :\n>> " + role + " <<");
        }
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        return credentials;

    }


}