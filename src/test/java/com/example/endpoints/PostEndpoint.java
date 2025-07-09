package com.example.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class PostEndpoint {

    public static Response createPost(String requestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts");
    }
}
