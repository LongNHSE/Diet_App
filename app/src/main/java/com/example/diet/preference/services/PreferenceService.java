package com.example.diet.preference.services;

import com.example.diet.preference.dto.PreferenceResponse;
import com.example.diet.response.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PreferenceService {
    @GET("/preference")
    Call<ResponseDTO<List<PreferenceResponse>>> getPreferences();
}
