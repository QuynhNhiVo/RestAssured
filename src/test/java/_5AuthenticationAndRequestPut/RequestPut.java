package _5AuthenticationAndRequestPut;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestPut {
    String token = "";

    @BeforeMethod
    public void loginGetToken(){
        POJO_Request login = new POJO_Request("anhtester", "Demo@123");
        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body(gson.toJson(login));

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);

        token = response.getBody().path("token");
        System.out.println(token);
    }

    @Test
    public void requestPut(){
        POJO_Request update = new POJO_Request("TJas",
                                                        "Jasmine",
                                                        "Brow",
                                                        "jsm2@gmail.com",
                                                        "Demo@123",
                                                        "0123456789",
                                                        1);
        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .header("Authorization", "Bearer "+token)
            .body(gson.toJson(update));

        Response response = request.when().put("/user/2");
        response.prettyPrint();
        request.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not True");
    }
}
