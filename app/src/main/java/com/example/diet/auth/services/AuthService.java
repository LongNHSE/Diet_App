package com.example.diet.auth.services;

import com.example.diet.auth.dto.LoginRequest;
import com.example.diet.auth.dto.LoginResponse;
import com.example.diet.auth.dto.SignUpRequest;
import com.example.diet.auth.dto.SignUpResponse;
import com.example.diet.response.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/signin")
    Call<ResponseDTO<LoginResponse>> loginUser(@Body LoginRequest loginRequest);

    @POST("auth/signup")
    Call<ResponseDTO<SignUpResponse>> signUpUser(@Body SignUpRequest signUpRequest);
}
