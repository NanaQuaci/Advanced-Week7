package com.example.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class PutEndpoint {

    public static Response updatePost(int id, String requestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .put("/posts/" + id);
    }
}
