package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class IntroSliderActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout dotsContainer;
    private ImageView[] dots;
    private List<SlideItem> slideItems;
    private MaterialButton btnNext;
    private MaterialButton btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_slider);

        viewPager = findViewById(R.id.viewPager);
        dotsContainer = findViewById(R.id.dotsContainer);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);

        // Initialize slide items
        slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.drawable.slide1, "Eat Healthy", "Maintaining good health should be the primary focus of everyone."));
        slideItems.add(new SlideItem(R.drawable.slide2, "Healthy Recipes", "Browse thousands of healthy recipes from all over the world."));
        slideItems.add(new SlideItem(R.drawable.slide3, "Track Your Health", "With amazing inbuilt tools you can track your progress."));

        // Set up the adapter for ViewPager
        viewPager.setAdapter(new SliderAdapter(slideItems));

        // Set up dot indicators
        setupDots(slideItems.size());
        updateDots(0);

        // Page change listener
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDots(position);
                updateButtons(position);
            }
        });

        // Skip button should navigate to MainActivity
        findViewById(R.id.btnSkip).setOnClickListener(v -> navigateToMainActivity());

        // Previous button action
        btnPrevious.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() > 0) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });

        // Next button action
        btnNext.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < slideItems.size() - 1) {
                viewPager.setCurrentItem(currentItem + 1, true);
            } else {
                navigateToMainActivity();
            }
        });
    }

    private void setupDots(int count) {
        dots = new ImageView[count];
        dotsContainer.removeAllViews();
        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_indicator_inactive));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            dotsContainer.addView(dots[i], params);
        }
    }

    private void updateDots(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_indicator_active));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_indicator_inactive));
            }
        }
    }

    private void updateButtons(int position) {
        if (position == 0) {
            btnPrevious.setEnabled(false);
            btnPrevious.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnPrevious.setStrokeColorResource(R.color.gray);
        } else {
            btnPrevious.setEnabled(true);
            btnPrevious.setTextColor(ContextCompat.getColor(this, R.color.mineral_green));
            btnPrevious.setStrokeColorResource(R.color.mineral_green);
        }

        if (position == slideItems.size() - 1) {
            btnNext.setText("Continue");
        } else {
            btnNext.setText("Next");
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(IntroSliderActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
