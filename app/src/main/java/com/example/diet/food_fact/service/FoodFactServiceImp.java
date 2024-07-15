package com.example.diet.food_fact.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodFactServiceImp {

    @GET("food_factor")
    Call<JsonObject> getAllFoodFacts();
}
