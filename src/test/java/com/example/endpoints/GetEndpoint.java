package com.example.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class GetEndpoint {

    public static Response getPostById(int id) {
        return given()
                .when()
                .get("/posts/" + id);
    }


    public static Response getAllPosts() {
        return given()
                .when()
                .get("/posts");
    }

}
