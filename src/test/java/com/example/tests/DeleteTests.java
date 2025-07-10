package com.example.tests;

import com.example.base.BaseTest;
import com.example.endpoints.DeleteEndpoint;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@Epic("REST API Testing")
@Feature("DELETE Requests")
public class DeleteTests extends BaseTest {

    @Story("Delete Post by ID")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that DELETE /posts/1 deletes a post successfully")
    @Test
    public void testDeletePost() {
        Response response = DeleteEndpoint.deletePost(1);

        response.then()
                .statusCode(200);
    }


    @Story("Handle invalid DELETE requests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that deleting a non-existing post still returns 200 (or 404 in real API)")
    @Test
    public void testDeleteNonExistingPost() {
        Response response = DeleteEndpoint.deletePost(9999);

        // JSONPlaceholder responds with 200 OK even if ID doesn't exist
        response.then()
                .statusCode(200); // In real APIs, expect 404 or 204
    }


    @Story("Delete non-existent resource")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that DELETE /posts/{id} on non-existent resource returns 200")
    @Test
    public void testDeleteNonExistentPost() {
        Response response = DeleteEndpoint.deletePost(9999);

        response.then()
                .statusCode(200); // JSONPlaceholder still returns 200
    }

    @Story("Delete with invalid ID format")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that DELETE with non-numeric ID returns client/server error")
    @Test
    public void testDeleteWithInvalidIdFormat() {
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .delete("/posts/abc");

        response.then()
                .statusCode(anyOf(equalTo(400), equalTo(404)));
    }

    @Story("Double delete on same resource")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that deleting the same resource twice returns consistent status")
    @Test
    public void testDoubleDelete() {
        Response first = DeleteEndpoint.deletePost(5);
        first.then().statusCode(200);

        Response second = DeleteEndpoint.deletePost(5);
        second.then().statusCode(200);
    }


}
