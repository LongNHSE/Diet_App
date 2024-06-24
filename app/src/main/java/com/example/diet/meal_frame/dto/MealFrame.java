package com.example.diet.meal_frame.dto;

import java.util.Date;

public class MealFrame {
    private String mealStandardId;
    private String mealStructureId;
    private double proportion;
    private String description;
    private int index;
    private boolean isActive;
    private String _id;
    private Date createdAt;
    private Date updatedAt;

    public MealFrame() {
    }

    public MealFrame(String mealStandardId, String mealStructureId, double proportion, String description, int index, boolean isActive, String _id, Date createdAt, Date updatedAt) {
        this.mealStandardId = mealStandardId;
        this.mealStructureId = mealStructureId;
        this.proportion = proportion;
        this.description = description;
        this.index = index;
        this.isActive = isActive;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getMealStandardId() {
        return mealStandardId;
    }

    public void setMealStandardId(String mealStandardId) {
        this.mealStandardId = mealStandardId;
    }

    public String getMealStructureId() {
        return mealStructureId;
    }

    public void setMealStructureId(String mealStructureId) {
        this.mealStructureId = mealStructureId;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
