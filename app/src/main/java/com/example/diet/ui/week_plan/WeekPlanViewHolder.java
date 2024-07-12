package com.example.diet.ui.week_plan;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;

public class WeekPlanViewHolder extends  RecyclerView.ViewHolder{
    TextView typeName;

    RecyclerView rvWeekPlanItemDetail;


    public WeekPlanViewHolder(@NonNull View itemView) {
        super(itemView);
        typeName = itemView.findViewById(R.id.typeName);
        rvWeekPlanItemDetail = itemView.findViewById(R.id.rv_week_plan_item_detail);
    }
}
