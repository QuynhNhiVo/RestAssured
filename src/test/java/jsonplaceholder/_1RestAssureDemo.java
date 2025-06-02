package jsonplaceholder;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class _1RestAssureDemo {
    @Test
    public void testGetPosts() {
        RestAssured
                .given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .log().body(); // In ra response body
    }

    @Test
    public void testCreatePost() {
        String requestBody = """
        {
          "title": "Tester",
          "body": "This is a test post",
          "userId": 1
        }
        """;

        RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void testUpdatePost() {
        String requestBody = """
        {
          "id": 1,
          "title": "Updated",
          "body": "Updated post content",
          "userId": 1
        }
        """;

        RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .log().body();
    }

}
