package _6RequestPatch_Delete;

import common.BaseTest;
import globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestDelete extends BaseTest {

    @Test
    public void requestDelete(){
        String username = "Tessautumn03";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .header("Authorization", "Bearer " + TokenGlobal.tokenGlobal)
            .queryParam("username", username);

        Response response = request.when().delete("/user");
        response.prettyPrint();
        request.then().statusCode(200);
        Assert.assertEquals(response.getBody().path("message"), "Success", "Message not match");

    }

}
