package spring.n4d3sh1k4.securities.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String password;
    private String confirmPassword;
    // getters and setters
}
