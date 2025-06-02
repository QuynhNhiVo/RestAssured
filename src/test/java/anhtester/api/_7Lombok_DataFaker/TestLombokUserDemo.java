package anhtester.api._7Lombok_DataFaker;

import com.google.gson.Gson;
import anhtester.api.common.BaseTest;
import globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.LombokRegister;
import model.LombokUser;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class TestLombokUserDemo extends BaseTest {

    @Test
    public void lombokTest(){
        LombokUser lombokUser = new LombokUser( "",
            "",
            "",
            "",
            "",
            "",
            1);
        System.out.println(lombokUser);
    }

    @Test
    public void dataFakerTest(){
        Faker faker = new Faker(new Locale("en"));

        String computer = faker.computer().windows();
        String name = faker.name().fullName();
        String fullAddress = faker.address().fullAddress();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        System.out.println(computer);
        System.out.println(name);
        System.out.println(fullAddress);
        System.out.println(email);
        System.out.println(password);
    }

    @Test
    public void requestPatchDataFake() {

        Faker faker = new Faker(new Locale("vi"));

        String phoneNumber = faker.phoneNumber().cellPhone();
        phoneNumber = phoneNumber.replace(" ", "");
        System.out.println(phoneNumber);

        LombokRegister lombokRegister = new LombokRegister();
        lombokRegister.setFirstName(faker.name().firstName());
        lombokRegister.setLastName(faker.name().lastName());
        lombokRegister.setEmail(faker.internet().emailAddress());
        lombokRegister.setPhone(phoneNumber);
        lombokRegister.setUserStatus(0);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .header("Authorization", "Bearer " + TokenGlobal.tokenGlobal)
            .body(gson.toJson(lombokRegister));

        Response response = request.when().patch("/user/10");
        response.prettyPrint();
        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
