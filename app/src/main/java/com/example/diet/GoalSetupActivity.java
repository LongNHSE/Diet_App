package com.example.diet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.diet.goal.dto.GoalResponse;
import com.example.diet.goal.services.GoalService;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoalSetupActivity extends AppCompatActivity {

    private TextView optionGainWeight;
    private TextView optionLoseWeight;
    private TextView optionMaintainHealth;
    private MaterialButton btnCompleteGoal;

    private SharedPreferences sharedPreferences;
    private GoalService goalService;
    private int selectedGoalSign;
    private String selectedGoalId;
    private String selectedGoalName;
    private int currentWeight;
    private float userBMI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setup);

        optionGainWeight = findViewById(R.id.optionGainWeight);
        optionLoseWeight = findViewById(R.id.optionLoseWeight);
        optionMaintainHealth = findViewById(R.id.optionMaintainHealth);
        btnCompleteGoal = findViewById(R.id.btnComplete);

        btnCompleteGoal.setOnClickListener(v -> navigateToGoalWeight());

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        currentWeight = sharedPreferences.getInt("currentWeight", 0); // Assume the current weight is stored
        userBMI = sharedPreferences.getFloat("userBMI", 0.0f); // Assume the BMI is stored

        goalService = RetrofitClient.getClient(token).create(GoalService.class);
        fetchGoals();
    }

    private void fetchGoals() {
        Call<ResponseDTO<List<GoalResponse>>> call = goalService.getGoals();
        call.enqueue(new Callback<ResponseDTO<List<GoalResponse>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<GoalResponse>>> call, Response<ResponseDTO<List<GoalResponse>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GoalResponse> goals = response.body().getData();
                    setupGoalOptions(goals);
                } else {
                    Toast.makeText(GoalSetupActivity.this, "Failed to fetch goals", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<GoalResponse>>> call, Throwable t) {
                Toast.makeText(GoalSetupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupGoalOptions(List<GoalResponse> goals) {
        for (GoalResponse goal : goals) {
            switch (goal.getGoalName()) {
                case "Gain":
                    optionGainWeight.setOnClickListener(v -> handleGoalSelection(goal.getGoalName(), goal.getSign(), goal.getId()));
                    break;
                case "Lose":
                    optionLoseWeight.setOnClickListener(v -> handleGoalSelection(goal.getGoalName(), goal.getSign(), goal.getId()));
                    break;
                case "Maintenance":
                    optionMaintainHealth.setOnClickListener(v -> handleGoalSelection(goal.getGoalName(), goal.getSign(), goal.getId()));
                    break;
            }
        }
        applyBMIRules();
    }

    private void applyBMIRules() {
        if (userBMI >= 25.0) { // Fat or obese
            optionGainWeight.setEnabled(false);
            optionGainWeight.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        } else if (userBMI < 18.5) { // Skinny
            optionLoseWeight.setEnabled(false);
            optionLoseWeight.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        }
    }

    private void handleGoalSelection(String goalName, int goalSign, String goalId) {
        selectedGoalSign = goalSign;
        selectedGoalId = goalId;
        selectedGoalName = goalName;

        // Ensure the disabled options retain their gray background
        if (userBMI >= 25.0) { // Fat or obese
            optionGainWeight.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        } else {
            optionGainWeight.setBackgroundResource(goalName.equals("Gain") ? R.drawable.selected_background : R.drawable.unselected_background);
        }

        if (userBMI < 18.5) { // Skinny
            optionLoseWeight.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        } else {
            optionLoseWeight.setBackgroundResource(goalName.equals("Lose") ? R.drawable.selected_background : R.drawable.unselected_background);
        }

        optionMaintainHealth.setBackgroundResource(goalName.equals("Maintenance") ? R.drawable.selected_background : R.drawable.unselected_background);
    }

    private void navigateToGoalWeight() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("goalSign", selectedGoalSign);
        editor.putString("goalId", selectedGoalId);
        editor.putString("goalName", selectedGoalName);
        editor.apply();

        if (selectedGoalSign == 0) {
            Intent intent = new Intent(GoalSetupActivity.this, GoalSuccessActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(GoalSetupActivity.this, GoalWeightActivity.class);
            intent.putExtra("goalSign", selectedGoalSign);
            intent.putExtra("currentWeight", currentWeight);
            startActivity(intent);
            finish();
        }
    }
}
