package com.example.diet.food.dto;

import com.example.diet.Type.dto.Type;
import com.example.diet.response.ResponseDTO;

public class Food {
    String _id;
    String typeId;

    String foodName;
    Integer size;
    Double caloricintake;

    Double carbohydrates;

    Double protein;

    Double fat;

    Double fiber;

    Double water;

    String icon;

    String description;

    Boolean isActive;

    public Food() {
    }

    public Food(String typeId, String foodName, Integer size, Double caloricintake, Double carbohydrates, Double protein, Double fat, Double fiber, Double water, String icon, String description, Boolean isActive) {
        this.typeId = typeId;
        this.foodName = foodName;
        this.size = size;
        this.caloricintake = caloricintake;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.fiber = fiber;
        this.water = water;
        this.icon = icon;
        this.description = description;
        this.isActive = isActive;
    }


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getCaloricintake() {
        return caloricintake;
    }

    public void setCaloricintake(Double caloricintake) {
        this.caloricintake = caloricintake;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Food{" +
                "_id='" + _id + '\'' +
                ", typeId='" + typeId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", size=" + size +
                ", caloricintake=" + caloricintake +
                ", carbohydrates=" + carbohydrates +
                ", protein=" + protein +
                ", fat=" + fat +
                ", fiber=" + fiber +
                ", water=" + water +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
