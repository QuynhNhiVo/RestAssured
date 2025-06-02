package anhtester.api._7Lombok_DataFaker;

import model.LombokRegister;
import net.datafaker.Faker;

import java.util.Locale;

public class LombokUserDemo {
    private static LombokRegister createUser(){
        Faker faker = new Faker(new Locale("vi"));
        String phoneNumber = "0" + faker.number().digits(9);
        return LombokRegister.builder()
            .username(faker.name().username())
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .phone(phoneNumber)
            .email(faker.internet().emailAddress())
            .userStatus(1)
            .build();
    }

    public static LombokRegister getUserDataUpdate() {
        Faker faker = new Faker(new Locale("vi"));
        String phoneNumber = faker.phoneNumber().cellPhone().replace(" ", "");
        return LombokRegister.builder()
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .phone(phoneNumber)
            .email(faker.internet().emailAddress())
            .userStatus(1)
            .build();
    }
}
