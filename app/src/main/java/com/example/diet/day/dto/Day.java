package com.example.diet.day.dto;

import com.example.diet.meal.dto.Meal;

import java.util.Date;

public class Day {
    private String id;
    private String dietId;
    private int index;
    private int totalCalStd;
    private double carbohydratesStd;
    private double fiberStd;
    private double proteinStd;
    private double fatStd;
    private int waterStd;
    private int totalCal;
    private double carbohydrates;
    private double fiber;
    private double protein;
    private double fat;
    private int water;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private Meal meal;
    // Getters and Setters

    public Day(String id, String dietId, int index, int totalCalStd, double carbohydratesStd, double fiberStd, double proteinStd, double fatStd, int waterStd, int totalCal, double carbohydrates, double fiber, double protein, double fat, int water, boolean isActive, Date createdAt, Date updatedAt, Meal meal) {
        this.id = id;
        this.dietId = dietId;
        this.index = index;
        this.totalCalStd = totalCalStd;
        this.carbohydratesStd = carbohydratesStd;
        this.fiberStd = fiberStd;
        this.proteinStd = proteinStd;
        this.fatStd = fatStd;
        this.waterStd = waterStd;
        this.totalCal = totalCal;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
        this.protein = protein;
        this.fat = fat;
        this.water = water;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.meal = meal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDietId() {
        return dietId;
    }

    public void setDietId(String dietId) {
        this.dietId = dietId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTotalCalStd() {
        return totalCalStd;
    }

    public void setTotalCalStd(int totalCalStd) {
        this.totalCalStd = totalCalStd;
    }

    public double getCarbohydratesStd() {
        return carbohydratesStd;
    }

    public void setCarbohydratesStd(double carbohydratesStd) {
        this.carbohydratesStd = carbohydratesStd;
    }

    public double getFiberStd() {
        return fiberStd;
    }

    public void setFiberStd(double fiberStd) {
        this.fiberStd = fiberStd;
    }

    public double getProteinStd() {
        return proteinStd;
    }

    public void setProteinStd(double proteinStd) {
        this.proteinStd = proteinStd;
    }

    public double getFatStd() {
        return fatStd;
    }

    public void setFatStd(double fatStd) {
        this.fatStd = fatStd;
    }

    public int getWaterStd() {
        return waterStd;
    }

    public void setWaterStd(int waterStd) {
        this.waterStd = waterStd;
    }

    public int getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(int totalCal) {
        this.totalCal = totalCal;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
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

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String toString() {
        return "DayDTO{" +
                "id='" + id + '\'' +
                ", dietId='" + dietId + '\'' +
                ", index=" + index +
                ", totalCalStd=" + totalCalStd +
                ", carbohydratesStd=" + carbohydratesStd +
                ", fiberStd=" + fiberStd +
                ", proteinStd=" + proteinStd +
                ", fatStd=" + fatStd +
                ", waterStd=" + waterStd +
                ", totalCal=" + totalCal +
                ", carbohydrates=" + carbohydrates +
                ", fiber=" + fiber +
                ", protein=" + protein +
                ", fat=" + fat +
                ", water=" + water +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", Meal=" + meal +
                '}';
    }
}
