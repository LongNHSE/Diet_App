package com.example.diet.goal.services;

import com.example.diet.goal.dto.GoalRequest;
import com.example.diet.goal.dto.GoalResponse;
import com.example.diet.response.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GoalService {
    @GET("/goal")
    Call<ResponseDTO<List<GoalResponse>>> getGoals();
}