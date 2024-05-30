package common;

import _5AuthenticationAndRequestPut.POJO_Request;
import com.google.gson.Gson;
import globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeMethod
    public static void loginGetToken(){
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

        TokenGlobal.tokenGlobal = response.getBody().path("token");
        System.out.println(TokenGlobal.tokenGlobal);
    }
}
