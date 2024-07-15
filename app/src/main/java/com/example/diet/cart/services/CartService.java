package com.example.diet.cart.services;

import com.example.diet.cart.dto.Cart;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface CartService {

    @GET("productDetail/my")
    Call<JsonObject> getProductDetails();

    @POST("productDetail")
    Call<Cart> createProductDetail(@Body Cart cart);

    @PATCH("productDetail")
    Call<Cart> updateProductDetail(@Body Cart cart);
}