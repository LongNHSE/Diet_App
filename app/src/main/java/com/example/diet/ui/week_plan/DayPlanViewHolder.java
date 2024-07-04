package com.example.diet.ui.week_plan;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;

public class DayPlanViewHolder extends  RecyclerView.ViewHolder{
    TextView typeName;
//    ImageView icon;
    TextView typeName2;
   TextView typeName3;
//    TextView typeName4;
//    TextView typeName5;
    public DayPlanViewHolder(@NonNull View itemView) {
        super(itemView);
       typeName = itemView.findViewById(R.id.activity);
        typeName2 = itemView.findViewById(R.id.mealtypeName);
//        icon = itemView.findViewById(R.id.img);
       typeName3 = itemView.findViewById(R.id.ingredients);
//        typeName4 = itemView.findViewById(R.id.total_calo);
//        typeName5 = itemView.findViewById(R.id.total_ingre);

    }
}
