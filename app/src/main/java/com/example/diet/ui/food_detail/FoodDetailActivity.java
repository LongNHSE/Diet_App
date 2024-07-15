package com.example.diet.ui.food_detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diet.R;
import com.example.diet.databinding.ActivityFoodDetailBinding;
import com.example.diet.food_detail.dto.FoodDetail;
import com.example.diet.food_detail.service.FoodDetailServiceImp;
import com.example.diet.util.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FoodDetailActivity extends AppCompatActivity {


    private ActivityFoodDetailBinding binding;


    private String foodDetailId ;
    private List<FoodDetail> foodDetailSubstitutes;
    private FoodDetailSubstituteAdapter foodDetailSubstituteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        foodDetailId = intent.getStringExtra("foodDetailId");


        // Initialize substitute list and substituteAdapter
        foodDetailSubstitutes = new ArrayList<>();
        foodDetailSubstituteAdapter = new FoodDetailSubstituteAdapter(foodDetailSubstitutes, FoodDetailActivity.this);


        // Set up RecyclerView
        RecyclerView recyclerView = binding.substituteRecyclerView;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3); // 3 columns
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(foodDetailSubstituteAdapter);


        // Load Food Detail
        loadFoodDetail(foodDetailId);


        // Load substitutes
        loadFoodDetailSubstitute();


    }

    private void loadFoodDetail(String foodDetailId) {
        Retrofit retrofit = RetrofitClient.getClient(null);
        FoodDetailServiceImp apiService = retrofit.create(FoodDetailServiceImp.class);
        Call<JsonObject> call = apiService.getFoodDetail(foodDetailId);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonObject data = jsonObject.getAsJsonObject("data");
                        FoodDetail detail;


                        Gson gson = new Gson();
                        detail = gson.fromJson(data, FoodDetail.class);


                        //Setting Data
                        Glide.with(FoodDetailActivity.this)
                                .load(detail.getIcon())
                                .into(binding.foodDetailAvatar);
                        binding.foodDetailName.setText(detail.getFood().getFoodName());
                        binding.foodDetailDescription.setText(detail.getFood().getDescription());
                        binding.calories.setText(String.valueOf(detail.getTotalCal()));
                        binding.carbohydrate.setText(String.valueOf(detail.getCarborhydrated()));
                        binding.fiber.setText(String.valueOf(detail.getFiber()));
                        binding.protein.setText(String.valueOf(detail.getProtein()));
                        binding.fat.setText(String.valueOf(detail.getFat()));
                        binding.water.setText(String.valueOf(detail.getWater()));


                    } else {
                        Log.d("Loading Food Details ID: " + foodDetailId, "Error");
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    private void loadFoodDetailSubstitute() {
        Retrofit retrofit = RetrofitClient.getClient(null);
        FoodDetailServiceImp apiService = retrofit.create(FoodDetailServiceImp.class);


        Call<JsonObject> call = apiService.getFoodDetailByMeal("6682bd308c5a607d9d1d78a4");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonArray dataArray = jsonObject.getAsJsonArray("data");
                        List<FoodDetail> substitutes = new ArrayList<>();


                        Gson gson = new Gson();
                        for (JsonElement element : dataArray) {
                            FoodDetail detail = gson.fromJson(element, FoodDetail.class);
                            substitutes.add(detail);
                        }


                        Log.d("", "substitutes: " + substitutes.size());
                        // Update RecyclerView
                        foodDetailSubstitutes.addAll(substitutes);
                        foodDetailSubstituteAdapter.notifyDataSetChanged();


                    } else {
                        Log.d("Loading Facts:", "Failed to get data from API");
                    }
                } else {
                    Log.d("Loading Facts:", "Failed API response");
                }
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Loading Facts:", "Failed to fetch data from API");
            }
        });
    }

}