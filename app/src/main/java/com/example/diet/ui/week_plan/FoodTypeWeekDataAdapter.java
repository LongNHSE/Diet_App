package com.example.diet.ui.week_plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.food_type.dto.food_type;

import java.util.List;

public class FoodTypeWeekDataAdapter extends RecyclerView.Adapter<WeekPlanViewHolder>{
    private List<food_type> foodTypeList;
    private RecyclerView recyclerView;
    private FoodWeekAdapter adapter;
    public FoodTypeWeekDataAdapter(List<food_type> foodTypeList) {
        this.foodTypeList = foodTypeList;
    }
    @NonNull
    @Override
    public WeekPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_plan_item, parent, false);
        return new WeekPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekPlanViewHolder holder, int position) {
        String typeName = foodTypeList.get(position).getname();
        holder.typeName.setText(typeName);
//        recyclerView = holder.itemView.findViewById(R.id.foodRecyclerView);
//        adapter = new FoodWeekAdapter();
//        recyclerView.setAdapter(adapter);
//        FoodWeekAdapter foodAdapter = new FoodWeekAdapter();
//        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
//        holder.recyclerView.setAdapter(foodAdapter);
    }

    @Override
    public int getItemCount() {
        return foodTypeList == null ? 0 : foodTypeList.size();
    }

    public void updateData(List<food_type> foodTypeList) {
        this.foodTypeList = foodTypeList;
        notifyDataSetChanged();
    }
}
