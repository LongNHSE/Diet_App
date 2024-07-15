package com.example.diet.coze.service;

import com.example.diet.coze.dto.CozeRequest;
import com.example.diet.coze.dto.CozeResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CozeApiService {
    @Headers({"Authorization: Bearer pat_lutbEyp3Ub5iPy0pG7l1BKFPnGkYz4Dd9TfuDoySWIfrlEflx9gKa0pdM0buPetL","Content-Type: application/json"})
    @POST("open_api/v2/chat")
    Call<CozeResponse> getRecommendations(@Body CozeRequest body);

    @Headers({"Authorization: Bearer pat_lutbEyp3Ub5iPy0pG7l1BKFPnGkYz4Dd9TfuDoySWIfrlEflx9gKa0pdM0buPetL","Content-Type: application/json"})
    @POST("open_api/v2/chat")
    Call<ResponseBody> getRecommendationsStream(@Body CozeRequest body);
}
