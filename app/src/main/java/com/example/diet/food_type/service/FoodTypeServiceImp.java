package com.example.diet.food_type.service;

import com.example.diet.food_type.dto.food_type;
import com.example.diet.response.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodTypeServiceImp {
    @GET("food_types")
    public Call<ResponseDTO<List<food_type>>> getAllFoodType();
}
