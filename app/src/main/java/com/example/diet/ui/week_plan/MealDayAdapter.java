package com.example.diet.ui.week_plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.meal.dto.Meal;

import java.util.List;

public class MealDayAdapter extends RecyclerView.Adapter<DayPlanViewHolder> {
    private List<Meal> mealList;

    @NonNull
    @Override
    public DayPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_day_plan, parent, false);
//        return new DayPlanViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DayPlanViewHolder holder, int position) {
//       holder.icon.setImageURI(Uri.parse(mealList.get(position).getIcon()));
//        implementation 'com.github.bumptech.glide:glide:4.12.0'
//        annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
    }

    @Override
    public int getItemCount() {
        return mealList == null ? 0 : mealList.size();
    }

    public void updateData(List<Meal> mealList) {
        this.mealList = mealList;
        notifyDataSetChanged();
    }
}
