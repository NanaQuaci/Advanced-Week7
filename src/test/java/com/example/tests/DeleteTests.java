package com.example.tests;

import com.example.base.BaseTest;
import com.example.endpoints.DeleteEndpoint;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


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

}
