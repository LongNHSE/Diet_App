package com.example.diet.auth.dto;

public class SignUpRequest {
    private String username;
    private String phone;
    private String dob;
    private String email;
    private String gender;
    private String password;

    public SignUpRequest(String username, String phone, String dob, String email, String gender, String password) {
        this.username = username;
        this.phone = phone;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
