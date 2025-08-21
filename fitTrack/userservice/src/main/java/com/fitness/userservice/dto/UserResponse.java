package com.fitness.userservice.dto;

import com.fitness.userservice.model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
public class UserResponse {

    private String id;

    private String email;

    private String lastName;
    private String firstName;

    private String password;


    private LocalDate createdAt;

    private LocalDate updatedAt;


}
