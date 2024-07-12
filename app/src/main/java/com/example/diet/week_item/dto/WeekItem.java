package com.example.diet.week_item.dto;

import com.example.diet.food_detail.dto.FoodDetail;


import java.util.ArrayList;

public class WeekItem {
    private String foodTypeName;

    private ArrayList<FoodDetail> foods;

    public WeekItem() {
    }

    public WeekItem(String foodTypeName, ArrayList<FoodDetail> foods) {
        this.foodTypeName = foodTypeName;
        this.foods = foods;
    }

    public String getName() {
        return foodTypeName;
    }

    public void setName(String name) {
        this.foodTypeName = name;
    }

    public ArrayList<FoodDetail> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<FoodDetail> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "WeekItem{" +
                "name='" + foodTypeName + '\'' +
                ", foods=" + foods +
                '}';
    }
}
