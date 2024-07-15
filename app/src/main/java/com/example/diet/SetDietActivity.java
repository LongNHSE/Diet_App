package com.example.diet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diet.diet.dto.DietRequest;
import com.example.diet.diet.dto.DietResponse;
import com.example.diet.diet.service.DietServiceImp;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.slider.Slider;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetDietActivity extends AppCompatActivity {

    private Slider durationSlider;
    private TextView durationValueTextView;
    private RadioGroup mainMealRadioGroup, sideMealRadioGroup, sessionRadioGroup;
    private EditText amountEditText;
    private Button saveButton;
    private SharedPreferences sharedPreferences;
    private String token;
    private float amountOfChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_diet);

        durationSlider = findViewById(R.id.durationSlider);
        durationValueTextView = findViewById(R.id.durationValueTextView);
        mainMealRadioGroup = findViewById(R.id.mainMealRadioGroup);
        sideMealRadioGroup = findViewById(R.id.sideMealRadioGroup);
        sessionRadioGroup = findViewById(R.id.sessionRadioGroup);
        amountEditText = findViewById(R.id.amountEditText);
        saveButton = findViewById(R.id.saveButton);
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);

        durationSlider.addOnChangeListener((slider, value, fromUser) -> {
            int weeks = (int) value;
            durationValueTextView.setText(weeks + " weeks");
        });

        updateAmountOfChange();

        saveButton.setOnClickListener(v -> {
            if (validateInputs()) {
                sendDietToBackend();
            }
        });
    }

    private void updateAmountOfChange() {
        String goalName = sharedPreferences.getString("goalName", "");
        int currentWeight = sharedPreferences.getInt("currentWeight", 0);
        int goalWeight = sharedPreferences.getInt("goalWeight", 0);

        if (goalName.equals("Maintenance")) {
            amountOfChange = 0.0f;
        } else if (goalName.equals("Gain") || goalName.equals("Lose")) {
            amountOfChange = Math.abs(goalWeight - currentWeight);
        }

        amountEditText.setText(String.valueOf(amountOfChange));
        amountEditText.setEnabled(false);
    }

    private boolean validateInputs() {
        if (amountEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Amount of Change is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sharedPreferences.getString("goalId", "").isEmpty()) {
            Toast.makeText(this, "Goal is not selected", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void sendDietToBackend() {
        int duration = (int) durationSlider.getValue();
        int mainMeals = mainMealRadioGroup.getCheckedRadioButtonId() == R.id.mainMeal2 ? 2 : 3;
        int sideMeals = sideMealRadioGroup.getCheckedRadioButtonId() == R.id.sideMeal1 ? 1 : 2;
        int sessions = sessionRadioGroup.getCheckedRadioButtonId() == R.id.session1 ? 1 :
                sessionRadioGroup.getCheckedRadioButtonId() == R.id.session2 ? 2 : 3;

        // Retrieve required data from SharedPreferences
        String userId = sharedPreferences.getString("userId", "");
        String preferenceId = sharedPreferences.getString("selectedPreferenceId", "");
        String activityLevelId = sharedPreferences.getString("activityLevelId", "");
        String goalId = sharedPreferences.getString("goalId", "");
        int goalSign = sharedPreferences.getInt("selectedGoalSign", 0);

        // Create DietRequest object
        DietRequest dietRequest = new DietRequest(activityLevelId, preferenceId, goalId, duration, mainMeals, sideMeals, sessions, (int) amountOfChange);

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
                    Toast.makeText(SetDietActivity.this, "Diet created successfully", Toast.LENGTH_SHORT).show();

                    // Navigate to DietSuccessActivity
                    Intent intent = new Intent(SetDietActivity.this, GeneratingPlanActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    try {
                        // Log error details
                        String errorBody = response.errorBody().string();
                        Log.e("DietError", errorBody);
                        Toast.makeText(SetDietActivity.this, "Failed to create diet: " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<DietResponse>> call, Throwable t) {
                // Handle failure
                Log.e("DietFailure", t.getMessage());
                Toast.makeText(SetDietActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
