package com.example.diet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.diet.goal.dto.GoalResponse;

import java.util.ArrayList;
import java.util.List;

public class GoalPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public GoalPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<GoalResponse> goals) {
        super(fragmentActivity);
        fragmentList.add(new Stage1Fragment(goals));
        fragmentList.add(new Stage2Fragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
