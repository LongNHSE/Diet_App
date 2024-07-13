package com.example.diet.ui.week_plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.food_type.dto.food_type;
import com.example.diet.ui.meal_info.OnItemClickListener;
import com.example.diet.week_item.dto.WeekItem;

import java.util.List;

public class FoodTypeWeekDataAdapter extends RecyclerView.Adapter<WeekPlanViewHolder>{
    private Context context;
    private List<WeekItem> foodTypeList;
    private RecyclerView recyclerView;

    private OnItemClickListener2 listener;

    private FoodWeekAdapter adapter;
    public FoodTypeWeekDataAdapter( List<WeekItem> foodTypeList, Context context, OnItemClickListener2 listener) {
        this.foodTypeList = foodTypeList;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public WeekPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_plan_item, parent, false);
        return new WeekPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekPlanViewHolder holder, int position) {

        WeekItem foodType = foodTypeList.get(position);
        String typeName = foodType.getName();
        holder.typeName.setText(typeName);

        holder.rvWeekPlanItemDetail.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.rvWeekPlanItemDetail.setAdapter(new MealDetailDataAdapter(context, foodType.getFoods(), listener));

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

    public void updateData( List<WeekItem> foodTypeList) {
        this.foodTypeList = foodTypeList;
        notifyDataSetChanged();
    }
}
