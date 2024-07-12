package com.example.diet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class GoalWeightActivity extends AppCompatActivity {

    private EditText goalWeightEditText;
    private MaterialButton btnCompleteGoalWeight, btnPreviousGoalWeight;
    private TextView weightAdvice;
    private int goalSign;
    private int currentWeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_weight);

        goalWeightEditText = findViewById(R.id.goalWeightEditText);
        btnCompleteGoalWeight = findViewById(R.id.btnCompleteGoalWeight);
        btnPreviousGoalWeight = findViewById(R.id.btnPreviousGoalWeight);
        weightAdvice = findViewById(R.id.weightAdvice);

        goalSign = getIntent().getIntExtra("goalSign", 0);
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        currentWeight = sharedPreferences.getInt("currentWeight", 0);

        provideWeightAdvice();

        btnCompleteGoalWeight.setOnClickListener(v -> validateAndCompleteGoal());
        btnPreviousGoalWeight.setOnClickListener(v -> navigateToGoalSetup());
    }

    private void provideWeightAdvice() {
        String adviceText;
        int start, end;

        if (goalSign == -1) {
            adviceText = String.format("As you aim to lose weight, a healthy goal range might be %d kg to %d kg.", currentWeight - 10, currentWeight - 5);
            start = adviceText.indexOf(String.valueOf(currentWeight - 10));
            end = adviceText.indexOf("kg") + 2;
        } else if (goalSign == 1) {
            adviceText = String.format("As you aim to gain weight, a healthy goal range might be %d kg to %d kg.", currentWeight + 5, currentWeight + 10);
            start = adviceText.indexOf(String.valueOf(currentWeight + 5));
            end = adviceText.indexOf("kg") + 2;
        } else {
            adviceText = "A healthy goal range depends on your individual needs. Please consult with a healthcare provider.";
            start = end = -1;
        }

        SpannableString spannableString = new SpannableString(adviceText);
        if (start != -1 && end != -1) {
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), start, start + (String.valueOf(currentWeight - 10).length() + 3), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), end - 3, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.mineral_green)), 0, adviceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        weightAdvice.setText(spannableString);
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
