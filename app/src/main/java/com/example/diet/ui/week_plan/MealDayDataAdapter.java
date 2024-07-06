package com.example.diet.ui.week_plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.meal.dto.Meal;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MealDayDataAdapter extends RecyclerView.Adapter<DayPlanViewHolder> {
    private List<Meal> mealList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String mealId);
        Meal getMeal(int position);
    }

    public MealDayDataAdapter(List<Meal> mealList, OnItemClickListener listener) {
        this.mealList = mealList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DayPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_plan_item, parent, false);
        return new DayPlanViewHolder(view, listener, mealList);
    }

    @Override
    public void onBindViewHolder(@NonNull DayPlanViewHolder holder, int position) {
        String combinedString = mealList.get(position).getMealStandard().getName() + "(" + mealList.get(position).getMealStandard().getDisplayTime() + ")";
        holder.typeName.setText(combinedString);
        String mealName = mealList.get(position).getMealFrame().getDescription();
        holder.typeName2.setText(mealName);
        String combinedStrings = String.valueOf(mealList.get(position).getMealFrame().getIndex()) + " ingredients";
        holder.typeName3.setText(combinedStrings);
    }

    @Override
    public int getItemCount() {
        return mealList == null ? 0 : mealList.size();
    }

    public void updateData(List<Meal> newMealList) {
        this.mealList.clear();
        this.mealList.addAll(newMealList);
        sortMealListByDisplayTime();
        notifyDataSetChanged();
    }

    private void sortMealListByDisplayTime() {
        if (mealList != null) {
            Collections.sort(mealList, new Comparator<Meal>() {
                @Override
                public int compare(Meal m1, Meal m2) {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
                    LocalTime time1 = LocalTime.parse(m1.getMealStandard().getDisplayTime(), timeFormatter);
                    LocalTime time2 = LocalTime.parse(m2.getMealStandard().getDisplayTime(), timeFormatter);
                    return time1.compareTo(time2);
                }
            });
        }
    }
}
