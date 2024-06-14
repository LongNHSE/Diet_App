package com.example.diet.food.service;

import com.example.diet.food.dto.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodService {

    @GET("foods")
    public Call<List<Food>> getAllFood();
}
