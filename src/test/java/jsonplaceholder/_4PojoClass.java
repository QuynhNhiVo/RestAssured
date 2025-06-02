package jsonplaceholder;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class _4PojoClass {
    @Test
    public void pojoTest(){
        POJO pojo = POJO.builder()
                .title("POJO")
                .body("This is created using Lombok and POJO")
                .userId(7)
                .build();
        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(pojo)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log().body();
    }
}
