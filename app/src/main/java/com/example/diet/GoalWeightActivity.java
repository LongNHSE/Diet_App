package com.example.diet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class GoalWeightActivity extends AppCompatActivity {

    private EditText goalWeightEditText;
    private MaterialButton btnCompleteGoalWeight, btnPreviousGoalWeight;
    private int goalSign;
    private int currentWeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_weight);

        goalWeightEditText = findViewById(R.id.goalWeightEditText);
        btnCompleteGoalWeight = findViewById(R.id.btnCompleteGoalWeight);
        btnPreviousGoalWeight = findViewById(R.id.btnPreviousGoalWeight);

        goalSign = getIntent().getIntExtra("goalSign", 0);
        currentWeight = getIntent().getIntExtra("currentWeight", 0);

        btnCompleteGoalWeight.setOnClickListener(v -> validateAndCompleteGoal());
        btnPreviousGoalWeight.setOnClickListener(v -> navigateToGoalSetup());
    }

    private void validateAndCompleteGoal() {
        String goalWeightStr = goalWeightEditText.getText().toString().trim();
        if (goalWeightStr.isEmpty()) {
            Toast.makeText(this, "Please enter a goal weight", Toast.LENGTH_SHORT).show();
            return;
        }

        int goalWeight = Integer.parseInt(goalWeightStr);

        if (goalSign == -1 && goalWeight >= currentWeight) {
            Toast.makeText(this, "Goal weight must be less than current weight for losing weight", Toast.LENGTH_SHORT).show();
        } else if (goalSign == 1 && goalWeight <= currentWeight) {
            Toast.makeText(this, "Goal weight must be more than current weight for gaining weight", Toast.LENGTH_SHORT).show();
        } else {
            completeGoalSetup(goalWeight);
        }
    }

    private void completeGoalSetup(int goalWeight) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("goalWeight", goalWeight);
        editor.apply();

        // Log all shared preferences in JSON format
        logSharedPreferences(sharedPreferences);

        Intent intent = new Intent(GoalWeightActivity.this, GoalSuccessActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToGoalSetup() {
        goalWeightEditText.setText("");

        Intent intent = new Intent(GoalWeightActivity.this, GoalSetupActivity.class);
        startActivity(intent);
        finish();
    }

    private void logSharedPreferences(SharedPreferences sharedPreferences) {
        Gson gson = new Gson();
        String json = gson.toJson(sharedPreferences.getAll());
        Log.d("SharedPreferences", json);
    }
}
