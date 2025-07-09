package com.example.tests;

import com.example.base.BaseTest;
import com.example.endpoints.GetEndpoint;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@Epic("REST API Testing")
@Feature("GET Requests")
public class GetTests extends BaseTest {

    @Story("Retrieve Post by ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that GET /posts/1 returns post with ID = 1")
    @Test
    public void testGetPostById() {
        Response response = GetEndpoint.getPostById(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }

    @Story("Handle invalid GET requests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that GET /posts/9999 returns 404 when post does not exist")
    @Test
    public void testGetPostWithInvalidId() {
        Response response = GetEndpoint.getPostById(9999);

        response.then()
                .statusCode(404); // JSONPlaceholder returns 404 for unknown resources
    }

}
