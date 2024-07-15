package com.example.diet.product.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductServiceImp {
    @GET("products/{id}")
    Call<JsonObject> getProductById(@Path("id") String productID);


    @GET("products")
    Call<JsonObject> getAllProducts();


    @GET("Product")
    Call<JsonObject> getRelatedProduct(@Query("type") String type);

}
