package com.example.diet.ui.meal_info;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.diet.databinding.ContentMealInfoBinding;
import com.example.diet.meal.dto.Meal;
import com.example.diet.meal.service.MealServiceImp;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.databinding.ActivityMealInfoBinding;
import com.example.diet.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealInfoActivity extends AppCompatActivity {

    Meal meal = new Meal();
    private ActivityMealInfoBinding binding;

    private String mealId;
    private ContentMealInfoBinding bindingMealInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMealInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bindingMealInfo = binding.contentMealInfo;
//
        setSupportActionBar(binding.toolbar);

        setUpMealInfo();
    }

    private void setUpMealInfo() {
        MealServiceImp mealServiceImp = RetrofitClient.getClient(null).create(MealServiceImp.class);
        mealId = getIntent().getStringExtra("mealId");
        Call<ResponseDTO<Meal>> call = mealServiceImp.getMealById(mealId);
        call.enqueue(new Callback<ResponseDTO<Meal>>() {
            @Override
            public void onResponse(Call<ResponseDTO<Meal>> call, Response<ResponseDTO<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    meal = response.body().getData();
                    if (meal != null) {
                        String totalCaloriesText = "Calories: " + meal.getTotalCal();
                        String standardCaloriesText = "Standard Calories: " + meal.getTotalCalstd();

                        bindingMealInfo.standardCaloMealInfo.setText(standardCaloriesText);
                        bindingMealInfo.totalCaloMealInfo.setText(totalCaloriesText);
                        double coverage = (meal.getTotalCal() / meal.getTotalCalstd()) * 100;
                        double roundedCoverage = Math.round(coverage * 100.0) / 100.0;
                        String coverageText = "Coverage: " + roundedCoverage + "%";
                        bindingMealInfo.coverageCaloMealInfo.setText(coverageText);
                        updateUIWithMealInfo();
                    } else {
                        Log.e("MealInfoActivity", "Meal data is null");
                    }
                } else {
                    Log.e("MealInfoActivity", "Response unsuccessful or body is null");
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<Meal>> call, Throwable t) {
                Log.e("MealInfoActivity", "onFailure: " + t.getMessage());
            }
        });
    }

    private void updateUIWithMealInfo() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_meal_info);
        if (recyclerView != null) {
            MealInfoAdapter adapter = new MealInfoAdapter(this, meal.getFoodDetails());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            Log.e("MealInfoActivity", "RecyclerView is null");
        }
    }
}
