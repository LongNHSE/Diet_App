package com.example.diet.food_detail.dto;

import com.example.diet.food.dto.Food;

import java.util.Date;

public class FoodDetail {

    private String _id;
    private String mealId;
    private String foodId;
    private double amount;
    private double totalCal;
    private double carborhydrated;
    private double fiber;
    private double protein;
    private double fat;
    private double water;
    private String icon;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    private Food food;

    public FoodDetail() {
    }

    public FoodDetail(String _id, String mealId, String foodId, double amount, double totalCal, double carborhydrated, double fiber, double protein, double fat, double water, String icon, String description, Date createdAt, Date updatedAt, Food food) {
        this._id = _id;
        this.mealId = mealId;
        this.foodId = foodId;
        this.amount = amount;
        this.totalCal = totalCal;
        this.carborhydrated = carborhydrated;
        this.fiber = fiber;
        this.protein = protein;
        this.fat = fat;
        this.water = water;
        this.icon = icon;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.food = food;
    }

    // Getters and setters for each field

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(double totalCal) {
        this.totalCal = totalCal;
    }

    public double getCarborhydrated() {
        return carborhydrated;
    }

    public void setCarborhydrated(double carborhydrated) {
        this.carborhydrated = carborhydrated;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "FoodDetail{" +
                "id='" + _id + '\'' +
                ", mealId='" + mealId + '\'' +
                ", foodId='" + foodId + '\'' +
                ", amount=" + amount +
                ", totalCal=" + totalCal +
                ", carborhydrated=" + carborhydrated +
                ", fiber=" + fiber +
                ", protein=" + protein +
                ", fat=" + fat +
                ", water=" + water +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", food=" + food +
                '}';
    }
}