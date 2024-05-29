package _4POJO;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoPOJO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;


    public DemoPOJO(String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public DemoPOJO(){
    }

    public DemoPOJO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Test
    public void registerPOJOWithGson(){
        DemoPOJO demoPOJO = new DemoPOJO("ThJas",
                                        "Jasmine",
                                        "Brow",
                                        "jsm@gmail.com",
                                        "Demo@123",
                                        "0123456789",
                                        1);
        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body(gson.toJson(demoPOJO));

        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
        Assert.assertTrue(message.contains("Success"), "Message not True");
    }

    @Test
    public void loginPOJOWithGson(){
        DemoPOJO demoPOJO = new DemoPOJO("TheJas", "Demo@123");

        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body(gson.toJson(demoPOJO));

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);

        String token = response.getBody().path("token");
        System.out.println(token);
    }
}
