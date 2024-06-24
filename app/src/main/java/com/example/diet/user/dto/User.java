package com.example.diet.user.dto;

import java.util.Date;

public class User {
    private String username;
    private Date dob;
    private String phone;
    private String email;
    private String password;
    private String gender;
    private boolean isActive;
    private String avatar;
    private String refreshToken;
    private String _id;
    private Date createdAt;
    private Date updatedAt;

    public User() {
    }

    public User(String username, Date dob, String phone, String email, String password, String gender, boolean isActive, String avatar, String refreshToken, String _id, Date createdAt, Date updatedAt) {
        this.username = username;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.isActive = isActive;
        this.avatar = avatar;
        this.refreshToken = refreshToken;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
