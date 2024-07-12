package com.example.diet.week_item.service;

import com.example.diet.response.ResponseDTO;
import com.example.diet.week_item.dto.WeekItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeekItemImp {

    @GET("diet/{dietId}/week")
    Call<ResponseDTO<List<WeekItem>>> getWeekItem(@Path("dietId") String dietId, @Query("index") Integer index);

}
