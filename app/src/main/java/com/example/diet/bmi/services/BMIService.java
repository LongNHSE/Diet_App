package com.example.diet.bmi.services;

import com.example.diet.bmi.dto.BMIRequest;
import com.example.diet.bmi.dto.BMIResponse;
import com.example.diet.response.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BMIService {
    @POST("/bmi")
    Call<ResponseDTO<BMIResponse>> createBMI(@Body BMIRequest bmiRequest);
    @GET("/bmi/my")
    Call<ResponseDTO<List<BMIResponse>>> getUserBMI();
}