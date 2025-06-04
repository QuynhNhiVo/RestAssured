package jsonplaceholder;

import helpers.SystemHelper;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class _3DataTest {

    @Test
    public void testPostFromJsonFile() throws IOException {
        String jsonBody = new String(Files.readAllBytes(Path.of(SystemHelper.getCurrentDir() + "/src/test/java/jsonplaceholder/data/userData.json")));

        RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log().body();
    }

}
