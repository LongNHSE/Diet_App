package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class GeneratingPlanActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private ImageView generateSuccessImage;
    private TextView successMessage;
    private MaterialButton goHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating_plan);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        generateSuccessImage = findViewById(R.id.generateSuccessImage);
        successMessage = findViewById(R.id.successMessage);
        goHomeButton = findViewById(R.id.goHomeButton);

        simulateProgress();
    }

    private void simulateProgress() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                if (progress < 100) {
                    progress++;
                    progressBar.setProgress(progress);
                    progressText.setText(progress + "%");
                    handler.postDelayed(this, 50);
                } else {
                    progressText.setText("100%");
                    generateSuccessImage.setVisibility(View.VISIBLE);
                    successMessage.setVisibility(View.VISIBLE);
                    goHomeButton.setVisibility(View.VISIBLE);
                    goHomeButton.setOnClickListener(v -> {
                        Intent intent = new Intent(GeneratingPlanActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    });
                }
            }
        }, 50);
    }
}
