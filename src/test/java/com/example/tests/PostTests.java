package com.example.tests;

import com.example.base.BaseTest;
import com.example.endpoints.PostEndpoint;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@Epic("REST API Testing")
@Feature("POST Requests")
public class PostTests extends BaseTest {


    @Story("Create New Post")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that POST /posts successfully creates a new post")
    @Test
    public void testCreatePost() {
        String requestBody = """
            {
              "title": "foo",
              "body": "bar",
              "userId": 1
            }
        """;

        Response response = PostEndpoint.createPost(requestBody);

        response.then()
                .statusCode(201)
                .body("title", equalTo("foo"));
    }


    @Story("Handle invalid POST requests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that POST /posts with missing title returns 400 or unexpected behavior")
    @Test
    public void testCreatePostWithMissingTitle() {
        String requestBody = """
        {
          "body": "missing title",
          "userId": 1
        }
    """;

        Response response = PostEndpoint.createPost(requestBody);

        // JSONPlaceholder still returns 201 (it's fake), but real APIs should return 400
        response.then()
                .statusCode(201)
                .body("body", equalTo("missing title"));
    }

}
