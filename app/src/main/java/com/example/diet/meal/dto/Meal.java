package com.example.diet.meal.dto;

import com.example.diet.food_detail.dto.FoodDetail;
import com.example.diet.meal_frame.dto.MealFrame;
import com.example.diet.meal_standard.dto.MealStandard;
import com.example.diet.meal_structure.dto.MealStructure;

import java.util.ArrayList;
import java.util.Date;

public class Meal {
    private String mealFrameId;
    private String dayId;
    private double totalCalstd;
    private double carbohydratedstd;
    private double fiberstd;
    private double proteinstd;
    private double fatstd;
    private double waterstd;
    private double totalCal;
    private double carbohydrated;
    private double fiber;
    private double protein;
    private double fat;
    private double water;
    private String _id;
    private Date createdAt;
    private Date updatedAt;
    private MealFrame mealFrame;
    private MealStandard mealStandard;
    private MealStructure mealStructure;
    private ArrayList<FoodDetail> foodDetails;


    public ArrayList<FoodDetail> getFoodDetails() {
        return foodDetails;
    }

    public void setFoodDetails(ArrayList<FoodDetail> foodDetails) {
        this.foodDetails = foodDetails;
    }

    public Meal() {
    }


    public Meal(String mealFrameId, String dayId, double totalCalstd, double carbohydratedstd, double fiberstd, double proteinstd, double fatstd, double waterstd, double totalCal, double carbohydrated, double fiber, double protein, double fat, double water, String _id, Date createdAt, Date updatedAt, MealFrame mealFrame, MealStandard mealStandard, MealStructure mealStructure, ArrayList<FoodDetail> foodDetails) {
        this.mealFrameId = mealFrameId;
        this.dayId = dayId;
        this.totalCalstd = totalCalstd;
        this.carbohydratedstd = carbohydratedstd;
        this.fiberstd = fiberstd;
        this.proteinstd = proteinstd;
        this.fatstd = fatstd;
        this.waterstd = waterstd;
        this.totalCal = totalCal;
        this.carbohydrated = carbohydrated;
        this.fiber = fiber;
        this.protein = protein;
        this.fat = fat;
        this.water = water;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.mealFrame = mealFrame;
        this.mealStandard = mealStandard;
        this.mealStructure = mealStructure;
        this.foodDetails = foodDetails;
    }

    public MealStandard getMealStandard() {
        return mealStandard;
    }

    public void setMealStandard(MealStandard mealStandard) {
        this.mealStandard = mealStandard;
    }

    public MealStructure getMealStructure() {
        return mealStructure;
    }

    public void setMealStructure(MealStructure mealStructure) {
        this.mealStructure = mealStructure;
    }

    public String getMealFrameId() {
        return mealFrameId;
    }

    public void setMealFrameId(String mealFrameId) {
        this.mealFrameId = mealFrameId;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public double getTotalCalstd() {
        return totalCalstd;
    }

    public void setTotalCalstd(double totalCalstd) {
        this.totalCalstd = totalCalstd;
    }

    public double getCarbohydratedstd() {
        return carbohydratedstd;
    }

    public void setCarbohydratedstd(double carbohydratedstd) {
        this.carbohydratedstd = carbohydratedstd;
    }

    public double getFiberstd() {
        return fiberstd;
    }

    public void setFiberstd(double fiberstd) {
        this.fiberstd = fiberstd;
    }

    public double getProteinstd() {
        return proteinstd;
    }

    public void setProteinstd(double proteinstd) {
        this.proteinstd = proteinstd;
    }

    public double getFatstd() {
        return fatstd;
    }

    public void setFatstd(double fatstd) {
        this.fatstd = fatstd;
    }

    public double getWaterstd() {
        return waterstd;
    }

    public void setWaterstd(double waterstd) {
        this.waterstd = waterstd;
    }

    public double getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(double totalCal) {
        this.totalCal = totalCal;
    }

    public double getCarbohydrated() {
        return carbohydrated;
    }

    public void setCarbohydrated(double carbohydrated) {
        this.carbohydrated = carbohydrated;
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

    public MealFrame getMealFrame() {
        return mealFrame;
    }

    public void setMealFrame(MealFrame mealFrame) {
        this.mealFrame = mealFrame;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealFrameId='" + mealFrameId + '\'' +
                ", dayId='" + dayId + '\'' +
                ", totalCalstd=" + totalCalstd +
                ", carbohydratedstd=" + carbohydratedstd +
                ", fiberstd=" + fiberstd +
                ", proteinstd=" + proteinstd +
                ", fatstd=" + fatstd +
                ", waterstd=" + waterstd +
                ", totalCal=" + totalCal +
                ", carbohydrated=" + carbohydrated +
                ", fiber=" + fiber +
                ", protein=" + protein +
                ", fat=" + fat +
                ", water=" + water +
                ", _id='" + _id + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", mealFrame=" + mealFrame +
                ", mealStandard=" + mealStandard +
                ", mealStructure=" + mealStructure +
                ", foodDetails=" + foodDetails +
                '}';
    }
}
