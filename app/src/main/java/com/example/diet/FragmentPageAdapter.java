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
    public FragmentPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("FragmentPageAdapter", "Position: " + position);

        if (position == 0) {
            return day_plan.newInstance("","");
        } else {
            return week_plan.newInstance("","");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
