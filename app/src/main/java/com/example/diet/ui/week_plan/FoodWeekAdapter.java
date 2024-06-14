package com.example.diet.ui.week_plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.food.dto.Food;
import com.example.diet.food.viewHolder.FoodViewHolder;

import java.util.List;

public class FoodWeekAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private List<Food> foodList;

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_detail_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        String typeName = foodList.get(position).getFoodName();
        holder.foodName.setText(typeName);
        holder.size.setText(foodList.get(position).getSize());
//        holder.icon.setImage(foodList.get(position).getIcon());
//        implementation 'com.github.bumptech.glide:glide:4.12.0'
//        annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
    }

    @Override
    public int getItemCount() {
        return foodList == null ? 0 : foodList.size();
    }

    public void updateData(List<Food> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }
}
