package com.example.diet;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.diet.ui.week_plan.day_plan;
import com.example.diet.ui.week_plan.week_plan;

public class FragmentPageAdapter extends FragmentStateAdapter {
    private String userId;

    private String dietId;
    private int dayIndex = 1; // Initial day count

    public FragmentPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String userId,String dietId) {
        super(fragmentManager, lifecycle);
        this.userId = userId;
        this.dietId = dietId;
    }

    public void navigateToMainActivity() {
        Log.d("MainActivity", "navigateToMainActivity");
        // startActivity(new Intent(this, MealInfoActivity.class));
        // finish();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return day_plan.newInstance(userId, dietId);
        } else {
            return week_plan.newInstance(userId, dietId);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
