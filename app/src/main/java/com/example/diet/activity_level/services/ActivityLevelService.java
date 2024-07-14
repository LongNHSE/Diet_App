package com.example.diet.activity_level.services;

import com.example.diet.activity_level.dto.ActivityLevelResponse;
import com.example.diet.response.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ActivityLevelService {
    @GET("/activity-level")
    Call<ResponseDTO<List<ActivityLevelResponse>>> getActivityLevels();
}
