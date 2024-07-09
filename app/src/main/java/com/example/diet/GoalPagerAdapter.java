package com.example.diet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class GoalPagerAdapter extends FragmentStateAdapter {

    public GoalPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Stage1Fragment();
            case 1:
                return new Stage2Fragment();
            case 2:
                return new Stage3Fragment();
            case 3:
                return new Stage4Fragment();
            default:
                return new Stage1Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
