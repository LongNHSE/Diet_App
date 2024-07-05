package com.example.diet.meal.service;

import com.example.diet.day.dto.Day;
import com.example.diet.meal.dto.Meal;
import com.example.diet.meal_standard.dto.MealStandard;
import com.example.diet.meal_structure.dto.MealStructure;
import com.example.diet.meal_frame.dto.MealFrame;
import com.example.diet.response.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealServiceImp {
    @GET("/path/to/endpoint")
    Call<ResponseDTO<Day>> getDayByIndexAndDietId(
            @Query("index") int index,
            @Query("dietId") String dietId
    );

    @GET("meal/day/{dayId}")
    public Call<ResponseDTO<List<Meal>>> getAllMealBasedOnDay(@Path("dayId") String dayId);

    @GET("meal/{mealId}")
    public Call<ResponseDTO<Meal>> getMealById(@Path("mealId") String mealId);

    @GET("meal/diet/{dietId}")
    Call<ResponseDTO<List<Meal>>> getAllMealBasedOnDietIdAndDayIndex(
            @Path("dietId") String dietId,
            @Query("dayIndex") int dayIndex
    );
}
