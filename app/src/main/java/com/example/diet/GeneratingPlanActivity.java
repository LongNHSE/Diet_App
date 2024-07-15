package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diet.diet.dto.DietRequest;
import com.example.diet.diet.dto.DietResponse;
import com.example.diet.diet.service.DietServiceImp;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneratingPlanActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private ImageView generateSuccessImage;
    private TextView successMessage;
    private MaterialButton goHomeButton;

    private String activityLevelId, preferenceId, goalId, token;
    private int duration, mainMeals, sideMeals, sessions, amountOfChange;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating_plan);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        generateSuccessImage = findViewById(R.id.generateSuccessImage);
        successMessage = findViewById(R.id.successMessage);
        goHomeButton = findViewById(R.id.goHomeButton);

        // Get data from intent
        activityLevelId = getIntent().getStringExtra("activityLevelId");
        preferenceId = getIntent().getStringExtra("preferenceId");
        goalId = getIntent().getStringExtra("goalId");
        duration = getIntent().getIntExtra("duration", 0);
        mainMeals = getIntent().getIntExtra("mainMeals", 0);
        sideMeals = getIntent().getIntExtra("sideMeals", 0);
        sessions = getIntent().getIntExtra("sessions", 0);
        amountOfChange = getIntent().getIntExtra("amountOfChange", 0);
        token = getIntent().getStringExtra("token");

        // Simulate progress and create diet
        simulateProgress();
    }

    private void simulateProgress() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                if (progress < 95) {
                    progress++;
                    progressBar.setProgress(progress);
                    progressText.setText(progress + "%");
                    handler.postDelayed(this, 50);
                } else {
                    createDiet();
                }
            }
        }, 50);
    }

    private void createDiet() {
        // Create DietRequest object
        DietRequest dietRequest = new DietRequest(activityLevelId, preferenceId, goalId, duration, mainMeals, sideMeals, sessions, amountOfChange);

        // Log the request body
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(dietRequest);
        Log.d("DietRequestBody", jsonRequest);

        DietServiceImp dietService = RetrofitClient.getClient(token).create(DietServiceImp.class);
        Call<ResponseDTO<DietResponse>> call = dietService.createDiet(dietRequest);

        call.enqueue(new Callback<ResponseDTO<DietResponse>>() {
            @Override
            public void onResponse(Call<ResponseDTO<DietResponse>> call, Response<ResponseDTO<DietResponse>> response) {
                if (response.isSuccessful()) {
                    DietResponse dietResponse = response.body().getData();

                    // Log the response body
                    String jsonResponse = gson.toJson(dietResponse);
                    Log.d("DietResponseBody", jsonResponse);

                    // Handle success
                    Toast.makeText(GeneratingPlanActivity.this, "Diet created successfully", Toast.LENGTH_SHORT).show();

                    // Update UI
                    handler.post(() -> {
                        progressBar.setProgress(100);
                        progressText.setText("100%");
                        generateSuccessImage.setVisibility(View.VISIBLE);
                        successMessage.setVisibility(View.VISIBLE);
                        goHomeButton.setVisibility(View.VISIBLE);
                        goHomeButton.setOnClickListener(v -> {
                            Intent intent = new Intent(GeneratingPlanActivity.this, MainFragment.class);
                            startActivity(intent);
                            finish();
                        });
                    });
                } else {
                    try {
                        // Log error details
                        String errorBody = response.errorBody().string();
                        Log.e("DietError", errorBody);
                        Toast.makeText(GeneratingPlanActivity.this, "Failed to create diet: " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<DietResponse>> call, Throwable t) {
                // Handle failure
                Log.e("DietFailure", t.getMessage());
                Toast.makeText(GeneratingPlanActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
