package com.example.jobtracker.dto;
import jakarta.validation.constraints.NotBlank;
public class JobRequest {

    @NotBlank
    private String company;
    @NotBlank
    private String role;
    @NotBlank
    private String city;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}