package anhtester.api._5AuthenticationAndRequestPut;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Authentication {

    @Test
    public void basicUnauthentication(){
        RequestSpecification request = given();
        Response response = request.when().get("https://postman-echo.com/basic-auth");
        System.out.println("Data from the Get API: \n" + response.getStatusCode() +"\n"+ response.body().asString());
        response.then().statusCode(200);
    }

    @Test
    public void basicAuthentication(){
        RequestSpecification request = given().auth().basic("postman", "password");
        Response response = request.when().get("https://postman-echo.com/basic-auth");
        System.out.println("Data from the Get API: \n" + response.getStatusCode() +"\n"+ response.body().asString());
        response.then().statusCode(200);
    }

    @Test
    public void digestAuthentication(){
        RequestSpecification request = RestAssured.given().auth().digest("postman", "password");
        Response response = request.when().get("https://postman-echo.com/basic-auth");
        System.out.println("Data from the Get API: \n" + response.getStatusCode() +"\n"+ response.body().asString());
        response.then().statusCode(200);
    }

//    @Test
//    public void formAuthentication(){
//        given().
//            auth().form("John", "Doe").
//            when().
//            get("https://the-internet.herokuapp.com/basic_auth");
//
//        given()
//            .auth()
//            .form("your username", "your password", new FormAuthConfig("/perform_signIn","user","password"));
//    }

    @Test
    public void OAuth1Authentication() {
        given()
            .auth()
            .oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
            .get("your end point URL");
    }

    @Test
    public void OAuth2Authentication() {
        given()
            .auth()
            .oauth2("Access token")
            .get("your end point URL");

    }

    @Test
    public void loginUserGetToken() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body("{\n" +
                "  \"username\": \"anhtester\",\n" +
                "  \"password\": \"Demo@123\"\n" +
                "}");

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void useTokenCookie(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .header("cookie", "194|tHWDnJRaLt8Ks6fNddRutwigiSD8tXzASxAVzlpb32d159c9")
            .body("");

        Response response = request.put("/user/1");
        response.prettyPrint();
    }

    @Test
    public void useTokenBearer(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .header("Authorization", "Bearer "+"token=193|LdZr9UeVXhmhIzw86iD0fiE9P8NFjCjQWErjoye907f3301f")
            .body("");

        Response response = request.put("/user/1");
        response.prettyPrint();
    }
}
