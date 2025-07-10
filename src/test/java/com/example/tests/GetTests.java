package com.example.tests;

import com.example.base.BaseTest;
import com.example.endpoints.GetEndpoint;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

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
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/post_schema.json"));
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


    @Story("Get all posts")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that GET /posts returns a list of posts")
    @Test
    public void testGetAllPosts() {
        Response response = GetEndpoint.getAllPosts();

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Story("Handle invalid GET requests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that GET /posts/0 returns 404")
    @Test
    public void testGetPostWithInvalidIdZero() {
        Response response = GetEndpoint.getPostById(0);

        response.then()
                .statusCode(404);
    }

    @Story("Handle non-existent GET requests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that GET /posts/9999 returns empty object")
    @Test
    public void testGetNonExistentPost() {
        Response response = GetEndpoint.getPostById(9999);

        response.then()
                .statusCode(404);
    }

}
