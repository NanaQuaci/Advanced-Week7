package com.example.tests;

import com.example.base.BaseTest;
import com.example.endpoints.PutEndpoint;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

@Epic("REST API Testing")
@Feature("PUT Requests")
public class PutTests extends BaseTest {

    @Story("Update Existing Post")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that PUT /posts/1 updates the post title successfully")
    @Test
    public void testUpdatePost() {
        String updatedData = """
            {
              "id": 1,
              "title": "updated",
              "body": "updated content",
              "userId": 1
            }
        """;

        Response response = PutEndpoint.updatePost(1, updatedData);

        response.then()
                .statusCode(200)
                .body("title", equalTo("updated"));
    }


    @Story("Handle invalid PUT requests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that updating a non-existing post returns 200 or unexpected behavior")
    @Test
    public void testUpdateNonExistingPost() {
        String requestBody = """
        {
          "id": 9999,
          "title": "Updated Title",
          "body": "Updated Body",
          "userId": 1
        }
    """;

        Response response = PutEndpoint.updatePost(9999, requestBody);

        // JSONPlaceholder returns 200 and echoes back, but real APIs would return 404
        response.then()
                .statusCode(200)
                .body("id", equalTo(9999))
                .body("title", equalTo("Updated Title"));
    }


    @Story("Update with partial data")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that PUT /posts/{id} updates only provided fields")
    @Test
    public void testUpdatePostWithPartialData() {
        String partialUpdate = """
        {
          "title": "Only Title Updated"
        }
    """;

        Response response = PutEndpoint.updatePost(1, partialUpdate);

        response.then()
                .statusCode(200)
                .body("title", equalTo("Only Title Updated"));
    }

    @Story("Update with invalid ID format")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that PUT with a non-numeric ID fails")
    @Test
    public void testUpdateWithInvalidIdFormat() {
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType("application/json")
                .body("""
            {
              "title": "Invalid ID",
              "body": "Test body",
              "userId": 1
            }
        """)
                .when()
                .put("/posts/abc");

        response.then()
                .statusCode(anyOf(equalTo(400), equalTo(404)));
    }

    @Story("Update with empty body")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that PUT /posts/{id} with an empty body still returns 200 on mock API")
    @Test
    public void testUpdateWithEmptyBody() {
        Response response = PutEndpoint.updatePost(1, "{}");

        response.then()
                .statusCode(200);
    }


}
