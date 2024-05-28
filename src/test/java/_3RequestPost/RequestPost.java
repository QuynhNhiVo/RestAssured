package _3RequestPost;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestPost {

    @Test
    public void requestPost(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com//api");
        request.accept("application/json");

        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void registerWithPostJson(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.accept("application/json")
            .contentType("application/json")
            .body("{" +
                    "\"username\": \"TheJasm\",\n" +
                    "\"firstName\": \"Jasm\",\n" +
                    "\"lastName\": \"Min\",\n" +
                    "\"email\": \"jam@email.com\",\n" +
                    "\"password\": \"Demo@123\",\n" +
                    "\"phone\": \"0912345678\",\n" +
                    " \"userStatus\": 1" +
                    "}");

        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void loginWithPost(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body("{" +
                        "\"username\": \"TheJasm\",\n" +
                        "\"password\": \"Demo@123\"}");

        Response response = request.when().post("/login");
        response.prettyPrint();
        request.then().statusCode(200);
    }
}
