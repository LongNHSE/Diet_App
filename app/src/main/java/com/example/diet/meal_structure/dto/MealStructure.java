package com.example.diet.meal_structure.dto;

import java.util.Date;

public class MealStructure {
    private int mainMealNo;
    private int sideMealNo;
    private String description;
    private boolean isActive;
    private String _id;
    private Date createdAt;
    private Date updatedAt;

    public MealStructure() {
    }

    public MealStructure(int mainMealNo, int sideMealNo, String description, boolean isActive, String _id, Date createdAt, Date updatedAt) {
        this.mainMealNo = mainMealNo;
        this.sideMealNo = sideMealNo;
        this.description = description;
        this.isActive = isActive;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getMainMealNo() {
        return mainMealNo;
    }

    public void setMainMealNo(int mainMealNo) {
        this.mainMealNo = mainMealNo;
    }

    public int getSideMealNo() {
        return sideMealNo;
    }

    public void setSideMealNo(int sideMealNo) {
        this.sideMealNo = sideMealNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
