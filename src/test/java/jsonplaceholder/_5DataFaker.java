package jsonplaceholder;

import io.restassured.RestAssured;
import jsonplaceholder.data.DemoPojo;
import net.datafaker.Faker;
import org.testng.annotations.Test;

public class _5DataFaker {
    @Test
    public void dataFakerTest(){
        Faker faker = new Faker();
        DemoPojo pojo = DemoPojo.builder()
                .title(faker.book().title())
                .body(faker.lorem().paragraph())
                .userId(faker.number().numberBetween(1, 100))
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
