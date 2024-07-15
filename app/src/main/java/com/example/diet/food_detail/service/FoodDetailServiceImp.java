package com.example.diet.food_detail.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface FoodDetailServiceImp {
    @GET("food-detail/meal/{id}")
    Call<JsonObject> getFoodDetailByMeal(@Path("id") String mealId);


    @GET("food-detail/{id}")
    Call<JsonObject> getFoodDetail(@Path("id") String foodDetailId);


    @GET("food-detail")
    Call<JsonObject> getFoodDetailSubstitute();

    @PATCH("food-detail/{foodDetailId}")
    Call<JsonObject> ChangeFoodDetail(@Path("foodDetailId") String foodDetailId, @Body JsonObject object);}
