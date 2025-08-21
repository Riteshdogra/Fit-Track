package com.fitness.userservice.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

     @Column(unique = true,nullable = false)
     private String email;

    private String lastName;
    private String firstName;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    @Enumerated(EnumType.STRING)
    private UserRole role=UserRole.USER;

}
