package com.example.diet.exercise.dto.service.service;

import com.example.diet.day.dto.Day;
import com.example.diet.exercise.dto.service.dto.Exercise;
import com.example.diet.response.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExerciseServiceImp {
    @GET("exercise/{exerciseId}")
    public Call<ResponseDTO<Exercise>> getMealById(@Path("exerciseId") String exerciseId);

}
