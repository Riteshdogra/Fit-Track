package com.fitness.userservice.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String keycloakId;

    private String firstName;
    private String lastName;
}
