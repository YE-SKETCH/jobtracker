package com.example.jobtracker.model;

public class Job {
    private Long id;
    private String company;
    private String role;
    private String city;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCompany() {
        return company;

    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCity() {
        return city;

    }public void setCity(String city) {
        this.city = city;
    }
    public String getRole() {
        return role;

    }
    public void setRole(String role) {
        this.role = role;
    }
}
