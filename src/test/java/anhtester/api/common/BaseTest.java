package anhtester.api.common;

import anhtester.api._5AuthenticationAndRequestPut.POJO_Request;
import com.google.gson.Gson;
import globals.ConfigGlobal;
import globals.TokenGlobal;
import helpers.PropertiesHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeSuite
    public void setupSuite(){
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    public static void loginGetToken(){
        POJO_Request login = new POJO_Request(ConfigGlobal.USERNAME, ConfigGlobal.PASSWORD);
        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri(ConfigGlobal.URI)
            .accept(ConfigGlobal.HEADER_JSON)
            .contentType(ConfigGlobal.HEADER_JSON)
            .body(gson.toJson(login));

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);

        TokenGlobal.tokenGlobal = response.getBody().path("token");
        System.out.println(TokenGlobal.tokenGlobal);
    }
}
