package com.example.diet.diet.service;

import com.example.diet.diet.dto.DietRequest;
import com.example.diet.diet.dto.DietResponse;
import com.example.diet.diet.dto.diet;
import com.example.diet.response.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DietServiceImp {

    @GET("diet")
    Call<ResponseDTO<diet>> getDiet();

    @GET("diet/my/latest")
    Call<ResponseDTO<diet>> getLatestDiet();

    @POST("diet")
    Call<ResponseDTO<DietResponse>> createDiet(@Body DietRequest dietRequest);

}
