package com.example.diet.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import android.util.Log;

public class AuthInterceptor implements Interceptor {

    private String authToken;

    public AuthInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + authToken);

        Request newRequest = builder.build();

        // Log the headers
        Log.d("AuthInterceptor", "Request Headers: " + newRequest.headers().toString());

        return chain.proceed(newRequest);
    }
}
