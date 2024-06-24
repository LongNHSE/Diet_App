package com.example.diet.meal_standard.dto;

import java.util.Date;

public class MealStandard {
    private String name;
    private String displayTime;
    private boolean isActive;
    private String _id;
    private Date createdAt;
    private Date updatedAt;


    public MealStandard() {
    }

    public MealStandard(String name, String displayTime, boolean isActive, String _id, Date createdAt, Date updatedAt) {
        this.name = name;
        this.displayTime = displayTime;
        this.isActive = isActive;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
