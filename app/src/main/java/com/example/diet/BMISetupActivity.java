package com.example.diet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diet.bmi.dto.BMIRequest;
import com.example.diet.bmi.dto.BMIResponse;
import com.example.diet.bmi.services.BMIService;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BMISetupActivity extends AppCompatActivity {

    private EditText weightEditText, heightEditText;
    private MaterialButton nextButton;
    private BMIService bmiService;
    private String userId;
    private String token;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_setup);

        weightEditText = findViewById(R.id.weight_edit_text);
        heightEditText = findViewById(R.id.height_edit_text);
        nextButton = findViewById(R.id.btnNext);

        userId = getIntent().getStringExtra("userId");
        token = getIntent().getStringExtra("token");

        bmiService = RetrofitClient.getClient(token).create(BMIService.class);

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        nextButton.setOnClickListener(v -> createBMI());
    }

    private void createBMI() {
        String weightStr = weightEditText.getText().toString().trim();
        String heightStr = heightEditText.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
            return;
        }

        int weight = Integer.parseInt(weightStr);
        int height = Integer.parseInt(heightStr);

        if (weight < 40 || weight > 250) {
            Toast.makeText(this, "Weight must be between 40kg and 250kg", Toast.LENGTH_SHORT).show();
            return;
        }

        if (height < 100 || height > 250) {
            Toast.makeText(this, "Height must be between 100cm and 250cm", Toast.LENGTH_SHORT).show();
            return;
        }

        BMIRequest bmiRequest = new BMIRequest(weight, height);

        Call<ResponseDTO<BMIResponse>> call = bmiService.createBMI(bmiRequest);
        call.enqueue(new Callback<ResponseDTO<BMIResponse>>() {
            @Override
            public void onResponse(Call<ResponseDTO<BMIResponse>> call, Response<ResponseDTO<BMIResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("BMISetupActivity", "BMI created successfully: " + new Gson().toJson(response.body()));
                    BMIResponse bmiResponse = response.body().getData();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("currentWeight", weight);
                    editor.putFloat("userBMI", (float) bmiResponse.getBmi());
                    editor.apply();

                    Intent intent = new Intent(BMISetupActivity.this, BMISuccessActivity.class);
                    intent.putExtra("BMI_VALUE", bmiResponse.getBmi());
                    intent.putExtra("userId", userId);
                    intent.putExtra("token", token);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("BMISetupActivity", "BMI creation failed: " + response.errorBody());
                    Toast.makeText(BMISetupActivity.this, "BMI creation failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<BMIResponse>> call, Throwable t) {
                Log.e("BMISetupActivity", "BMI creation error: " + t.getMessage());
            }
        });
    }
}
