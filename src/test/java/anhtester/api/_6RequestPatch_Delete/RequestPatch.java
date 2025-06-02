package anhtester.api._6RequestPatch_Delete;

import anhtester.api._5AuthenticationAndRequestPut.POJO_Request;
import com.google.gson.Gson;
import anhtester.api.common.BaseTest;
import globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestPatch extends BaseTest {

    @Test
    public void requestPatch(){
        POJO_Request pojoPatch = new POJO_Request("Tes",
                                                    "Pach",
                                                    "tpat@gmail.com",
                                                    "0231457689",
                                                            1);
        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .header("Authorization", "Bearer "+ TokenGlobal.tokenGlobal)
            .body(gson.toJson(pojoPatch));

        Response response = request.when().patch("/user/2");
        response.prettyPrint();
        response.then().statusCode(200);

        Assert.assertEquals(response.getBody().path("message"), "Success", "Message not match");
    }
}
