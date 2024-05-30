package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LombokUser {

    private final String username;
    private final String firstName;
    private final String lastName;
    private String email;
    private final String password;
    private String phone;
    private int userStatus;
}
