package model.data;

import model.LombokRegister;
import net.datafaker.Faker;

import java.util.Locale;

public class Lombok_Register {
    public static LombokRegister getUserData(){
        Faker faker = new Faker(new Locale("en"));

        String phoneNumber = faker.phoneNumber().cellPhone();
        phoneNumber = phoneNumber.replace(" ", "");

        return LombokRegister.builder()
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .phone(phoneNumber)
            .email(faker.internet().emailAddress())
            .userStatus(1)
            .build();
    }
}
