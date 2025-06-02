package anhtester.api._10ValidateJSONSchema;

import anhtester.api.common.BaseTest;
import globals.ConfigGlobal;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class ValidateJsonSchema extends BaseTest {
    @Test
    public void validateJson_GetBook_ID(){
        //Get source "src/test/resources"
        InputStream GetBookIDSchema = getClass().getClassLoader().getResourceAsStream("jsonfile/GetBookIDSchema.json");

        Response response = given()
            .baseUri(ConfigGlobal.URI)
            .when()
            .get("/book/10")
            .then()
            .statusCode(200)
            .and()
            .extract()
            .response();


        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetBookIDSchema));
    }

    @Test
    public void validateJson_GetAllBook(){
        InputStream GetAllBook = getClass().getClassLoader().getResourceAsStream("jsonfile/GetAllBookSchema.json");

        Response response = given()
            .baseUri(ConfigGlobal.URI)
            .when()
            .get("/books")
            .then()
            .statusCode(200)
            .and()
            .extract().response();
        response.prettyPrint();
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetAllBook));
    }
}
