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
import com.google.android.material.slider.Slider;
import com.google.gson.Gson;

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

        // Set the initial value of the duration slider to 1 and update the TextView
        durationSlider.setValue(1);
        durationValueTextView.setText("1 week");

        durationSlider.addOnChangeListener((slider, value, fromUser) -> {
            int weeks = (int) value;
            durationValueTextView.setText(weeks + " week" + (weeks > 1 ? "s" : ""));
        });

        updateAmountOfChange();

        saveButton.setOnClickListener(v -> {
            if (validateInputs()) {
                Intent intent = new Intent(SetDietActivity.this, GeneratingPlanActivity.class);
                intent.putExtra("activityLevelId", sharedPreferences.getString("activityLevelId", ""));
                intent.putExtra("preferenceId", sharedPreferences.getString("selectedPreferenceId", ""));
                intent.putExtra("goalId", sharedPreferences.getString("goalId", ""));
                intent.putExtra("duration", (int) durationSlider.getValue());
                intent.putExtra("mainMeals", mainMealRadioGroup.getCheckedRadioButtonId() == R.id.mainMeal2 ? 2 : 3);
                intent.putExtra("sideMeals", sideMealRadioGroup.getCheckedRadioButtonId() == R.id.sideMeal1 ? 1 : 2);
                intent.putExtra("sessions", sessionRadioGroup.getCheckedRadioButtonId() == R.id.session1 ? 1 :
                        sessionRadioGroup.getCheckedRadioButtonId() == R.id.session2 ? 2 : 3);
                intent.putExtra("amountOfChange", (int) amountOfChange);
                intent.putExtra("token", token);
                startActivity(intent);
                finish();
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
}
