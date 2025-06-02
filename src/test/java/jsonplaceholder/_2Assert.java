package jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class _2Assert {
    @Test
    public void testExtractJsonData() {
        Response response = RestAssured
                .given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1");

        JsonPath json = response.jsonPath();
        String title = json.getString("title");
        int userId = json.getInt("userId");
        System.out.println(userId);
        System.out.println(title);
        Assert.assertEquals(userId, 1);
        Assert.assertTrue(title.contains("provident"));
    }

}
