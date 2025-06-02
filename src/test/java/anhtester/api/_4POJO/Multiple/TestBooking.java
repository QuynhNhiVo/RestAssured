package anhtester.api._4POJO.Multiple;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestBooking {

    @Test
    public void bookingPOJO(){
        String baseUri = "https://restful-booker.herokuapp.com";
        String value = "application/json";

        RequestSpecification request = given();
        request.baseUri(baseUri)
            .accept(value)
            .contentType(value);

        BookingBody bookingBody = new BookingBody("The",
                                                    "Boost",
                                                    1000,
                                                    true,
                                                    "Literature");

        BookingDates bookingDates = new BookingDates("2024-02-05",
                                                    "2024-.7-06");

        bookingBody.setBookingdates(bookingDates);

        Gson gson = new Gson();

        request.body(gson.toJson(bookingBody));

        Response response = request.when().post("/booking");
        response.prettyPrint();
        request.then().statusCode(200);
    }
}
