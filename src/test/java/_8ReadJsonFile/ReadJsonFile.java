package _8ReadJsonFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import common.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ReadJsonFile extends BaseTest {
    @Test
    public void testLoginUser() {
        //Read Json file path
        String filePath = "src/test/resources/jsonfile/login.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body(new File(filePath));

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void testRegisterUser() {
        //Read Json file path
        String filePath = "src/test/resources/jsonfile/RegisterUser.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body(new File(filePath));

        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void testUpdateValueAdditionalInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty("additionalneeds", "Update New Value");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFileEdited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateValueInJson03_ArrayObject() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile03.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to JsonArray
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

            System.out.println("Original JSON: " + jsonArray);

            // Cập nhật giá trị của trường "lastname" trong phần tử đầu tiên
            if (jsonArray.size() > 0) {
                //Lấy vị trí object thứ nhất
                JsonObject firstObject = jsonArray.get(0).getAsJsonObject();
                firstObject.addProperty("lastname", "NewLastName");
            }

            System.out.println("Modified JSON: " + jsonArray);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile03.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            //Lưu đối tượng jsonArray chứ không phải jsonObject
            outputStream.write(gson.toJson(jsonArray).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
