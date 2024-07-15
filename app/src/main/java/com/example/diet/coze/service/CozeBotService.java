package com.example.diet.coze.service;

import com.example.diet.coze.dto.CozeRequest;
import com.example.diet.coze.dto.CozeResponse;
import com.example.diet.coze.dto.Message;
import com.example.diet.util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CozeBotService {
    private CozeApiService cozeApiService;
    public interface CozeBotCallback {
        void onSuccess(List<Message> messages);
        void onFailure(Throwable t);
    }


    public void sendRequest(CozeBotCallback callback, String[] foodList) {
        Retrofit retrofit = RetrofitClient.getClient2("https://api.coze.com/");
        cozeApiService = retrofit.create(CozeApiService.class);


        String base = "Please recommend some dishes based upon these ingredients: ";
        StringBuilder stringBuilder = new StringBuilder(base);
        for (int i = 0; i < foodList.length; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(foodList[i]);
        }


        String finalSentence = stringBuilder.toString();


        CozeRequest requestBody = new CozeRequest(
                "7391490633124462600", // conversation_id
                "7391477007630024711", // bot_id
                "user001",           // user
                finalSentence, // query
                false                  // stream
        );


        Call<CozeResponse> call = cozeApiService.getRecommendations(requestBody);
        call.enqueue(new Callback<CozeResponse>() {
            @Override
            public void onResponse(Call<CozeResponse> call, Response<CozeResponse> response) {
                if (response.isSuccessful()) {
                    CozeResponse cozeResponse = response.body();
                    if (cozeResponse != null) {
                        callback.onSuccess(cozeResponse.getMessages());
                    } else {
                        callback.onFailure(new Exception("No response from server"));
                    }
                } else {
                    callback.onFailure(new Exception("Request failed with code: " + response.code()));
                }
            }


            @Override
            public void onFailure(Call<CozeResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
