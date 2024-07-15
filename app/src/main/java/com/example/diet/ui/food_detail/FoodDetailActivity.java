package com.example.diet.ui.food_detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
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

public class FoodDetailActivity extends AppCompatActivity implements FoodDetailSubstituteAdapter.OnSubstituteUpdateListener {

    private ActivityFoodDetailBinding binding;
    private String foodDetailId;
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
        foodDetailSubstituteAdapter = new FoodDetailSubstituteAdapter(foodDetailSubstitutes, FoodDetailActivity.this, this);

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

    // Method to load food detail
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
                        FoodDetail detail = new Gson().fromJson(data, FoodDetail.class);

                        // Setting Data
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
                        Log.d("FoodDetailActivity", "Loading Food Details ID: " + foodDetailId + " failed");
                    }
                } else {
                    Log.d("FoodDetailActivity", "Failed API response while loading Food Details ID: " + foodDetailId);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("FoodDetailActivity", "Failed to load Food Detail", t);
            }
        });
    }

    // Method to load food detail substitutes
    private void loadFoodDetailSubstitute() {
        Retrofit retrofit = RetrofitClient.getClient(null);
        FoodDetailServiceImp apiService = retrofit.create(FoodDetailServiceImp.class);
        Call<JsonObject> call = apiService.getFoodDetailByMeal("6682bd308c5a607d9d1d78a4"); // Example meal ID, replace with actual logic
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

                        Log.d("FoodDetailActivity", "Substitutes loaded: " + substitutes.size());
                        // Update RecyclerView
                        foodDetailSubstitutes.addAll(substitutes);
                        foodDetailSubstituteAdapter.notifyDataSetChanged();

                    } else {
                        Log.d("FoodDetailActivity", "Failed to get data from API for substitutes");
                    }
                } else {
                    Log.d("FoodDetailActivity", "Failed API response while loading substitutes");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("FoodDetailActivity", "Failed to fetch data from API for substitutes", t);
            }
        });
    }

    // Method to handle substitute update
    @Override
    public void onSubstituteUpdate(String foodId) {
        // Implement your update logic here
        Log.d("FoodDetailActivity", "Updating food with ID: " + foodId);
        Retrofit retrofitClient = RetrofitClient.getClient(null);
        FoodDetailServiceImp apiService = retrofitClient.create(FoodDetailServiceImp.class);

        JsonObject object = new JsonObject();
        object.addProperty("foodId", foodId);

        Call<JsonObject> call = apiService.ChangeFoodDetail(foodDetailId, object);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonObject data = jsonObject.getAsJsonObject("data");
                        String newFoodDetailId = data.get("_id").getAsString();
                        Log.d("FoodDetailActivity", "Food updated successfully");
                        loadFoodDetail(newFoodDetailId);
                        loadFoodDetailSubstitute();
                    } else {
                        Log.d("FoodDetailActivity", "Failed to update food");
                    }
                } else {
                    Log.d("FoodDetailActivity", "Failed API response while updating food");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("FoodDetailActivity", "Failed to update food", t);
            }
        });


        // You can perform any update operations here, like updating UI or sending requests
    }
}
