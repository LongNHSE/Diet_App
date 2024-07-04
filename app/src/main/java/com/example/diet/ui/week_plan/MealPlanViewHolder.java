package com.example.diet.ui.week_plan;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;

public class MealPlanViewHolder extends  RecyclerView.ViewHolder{
    TextView typeName4;
    TextView typeName5;
    public MealPlanViewHolder(@NonNull View itemView) {
        super(itemView);
        typeName4 = itemView.findViewById(R.id.total_calo);
        typeName5 = itemView.findViewById(R.id.total_ingre);

    }
}
