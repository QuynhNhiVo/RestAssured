package _1RequestGet;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestGet {

    @Test
    public void testGetWithHeader(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/users");
//            .header("accept", "application/json");
        request.accept("application/json");

        Response response = request.when().get();
        response.prettyPrint();
    }

    @Test
    public void testGetWithParameter(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/users");
//            .header("accept", "application/json");
        request.accept("application/json");

        request.queryParam("username", "anhtester");

        Response response = request.when().get();
        response.prettyPrint();
    }

    @Test
    public void testGetWithVerifyResponse(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/users");
//            .header("accept", "application/json");
        request.accept("application/json");

        request.queryParam("username", "anhtester");

        Response response = request.when().get();
        response.prettyPrint();
        response.then().statusCode(200);
        response.then().contentType("application/json");
        response.then().body("response.username", equalTo("anhtester"));
    }
}
