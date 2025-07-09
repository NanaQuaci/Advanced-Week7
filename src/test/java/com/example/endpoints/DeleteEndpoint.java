package com.example.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class DeleteEndpoint {

    public static Response deletePost(int id) {
        return given()
                .when()
                .delete("/posts/" + id);
    }
}
