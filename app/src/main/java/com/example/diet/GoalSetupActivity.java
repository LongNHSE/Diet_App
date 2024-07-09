package com.example.diet;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;

public class GoalSetupActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private GoalPagerAdapter adapter;
    private ProgressBar progressBar;
    private MaterialButton nextStepButton;
    private MaterialButton backStepButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setup);

        viewPager = findViewById(R.id.viewPager);
        adapter = new GoalPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Disable user input for ViewPager2
        viewPager.setUserInputEnabled(false);

        progressBar = findViewById(R.id.progressBar);
        nextStepButton = findViewById(R.id.btnNext);
        backStepButton = findViewById(R.id.btnPrevious);

        nextStepButton.setOnClickListener(v -> goToNextStage());
        backStepButton.setOnClickListener(v -> goToPreviousStage());

        updateProgress();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateButtonsState(position);
            }
        });
    }

    public void goToNextStage() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem < adapter.getItemCount() - 1) {
            viewPager.setCurrentItem(currentItem + 1);
            updateProgress();
        } else {
            completeSetup();
        }
    }

    public void goToPreviousStage() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem > 0) {
            viewPager.setCurrentItem(currentItem - 1);
            updateProgress();
        }
    }

    public void completeSetup() {
        // Handle setup completion
        // For example, navigate to another activity or save the data
    }

    private void updateProgress() {
        int currentItem = viewPager.getCurrentItem();
        progressBar.setProgress(currentItem + 1);
        if (currentItem == adapter.getItemCount() - 1) {
            nextStepButton.setText("Complete");
        } else {
            nextStepButton.setText("Next Step");
        }
    }

    private void updateButtonsState(int position) {
        if (position == 0) {
            backStepButton.setEnabled(false);
            backStepButton.setTextColor(ContextCompat.getColor(this, R.color.gray));
            backStepButton.setStrokeColor(ContextCompat.getColorStateList(this, R.color.gray));
        } else {
            backStepButton.setEnabled(true);
            backStepButton.setTextColor(ContextCompat.getColor(this, R.color.mineral_green));
            backStepButton.setStrokeColor(ContextCompat.getColorStateList(this, R.color.mineral_green));
        }

        if (position == adapter.getItemCount() - 1) {
            nextStepButton.setText("Complete");
        } else {
            nextStepButton.setText("Next Step");
        }
    }
}
