
package com.example.diet.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:8000/";

    public static Retrofit getClient(String authToken) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // Add AuthInterceptor with JWT token
        if (authToken != null) {
            httpClient.addInterceptor(new AuthInterceptor(authToken));
        }


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}

