package anhtester.api._2VerifyResponse;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class VerifyResponse {

    @Test
    public void verifyResponseUseThen(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json");
        int id = 1;
        Response response = request.when().get("/book/"+id);
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().contentType("application/json");

        response.then().body("response.name", containsString("Clay Goodwin"));
        response.then().body("response.price", equalTo(799));
        response.then().body("response.image[0].path", containsString("public/images"));
    }

    @Test
    public void verifyResponseUseAssert(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json");
        int id = 1;
        Response response = request.when().get("/book/"+id);
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status Code Incorrect");
        Assert.assertEquals(response.getContentType(), "application/json", "Content Type Incorrect");

        ResponseBody body = response.getBody();
        Assert.assertTrue(body.asString().contains("Success"), "Message Success Not Exist");
    }

    @Test
    public void verifyResponseUseJsonPath(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json");
        int id = 1;
        Response response = request.when().get("/book/"+id);
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status Code Incorrect");
        Assert.assertEquals(response.getContentType(), "application/json", "Content Type Incorrect");

        JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.get("response.name");
        System.out.println("NAME: " + name);
        Assert.assertTrue(name.contains("Clay Goodwin"), "Name not True");

        Assert.assertEquals(jsonPath.get("response.price").toString(), "799", "Price Incorrect");

        String imagePath = jsonPath.get("response.image[1].path");
        Assert.assertTrue(imagePath.contains("public/images/"), "Images Path not True");
    }
}
