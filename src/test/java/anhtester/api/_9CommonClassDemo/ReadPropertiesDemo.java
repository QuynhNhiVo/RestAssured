package anhtester.api._9CommonClassDemo;

import com.google.gson.Gson;
import globals.ConfigGlobal;
import globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.LombokRegister;
import model.data.Lombok_Register;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class ReadPropertiesDemo extends BaseTestDemo{
    @Test
    public void Test_DemoPatch(){
        LombokRegister register = Lombok_Register.getUserData();
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigGlobal.URI)
            .accept(ConfigGlobal.HEADER_JSON)
            .contentType(ConfigGlobal.HEADER_JSON)
            .header("Authorization", "Bearer " + TokenGlobal.tokenGlobal)
            .body(gson.toJson(register));

        Response response = request.when().patch("/user/5");
        response.prettyPrint();
        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
