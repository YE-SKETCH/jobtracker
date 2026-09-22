package com.example.jobtracker.entity;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

    public String getUsername() {
        return "";
    }

    public @Nullable String getPassword() {
        return "";
    }

    public String getRole() {
        return "";
    }
}