package com.example.diet.meal.service;

import com.example.diet.meal.dto.Meal;
import com.example.diet.response.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MealServiceImp {

    @GET("meal/day/{dayId}")
    public Call<ResponseDTO<List<Meal>>> getAllMealBasedOnDay(@Path("dayId") String dayId);

    @GET("meal/{mealId}")
    public Call<ResponseDTO<Meal>> getMealById(@Path("mealId") String mealId);

}
