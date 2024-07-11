package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class GoalSuccessActivity extends AppCompatActivity {

    private MaterialButton finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_success);

        finishButton = findViewById(R.id.finishButton);

        finishButton.setOnClickListener(v -> {
            Intent intent = new Intent(GoalSuccessActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
