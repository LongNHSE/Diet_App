package com.example.diet.ui.food_fact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diet.R;
import com.example.diet.food_fact.dto.FoodFact;

import java.util.List;

public class FoodFactAdapter extends RecyclerView.Adapter<FoodFactAdapter.ViewHolder>{


    List<FoodFact> facts;
    Context context;


    public FoodFactAdapter(List<FoodFact> facts, Context context) {
        this.facts = facts;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_fact_item,parent,false);


        FoodFactAdapter.ViewHolder viewHolder = new FoodFactAdapter.ViewHolder(view);


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodFact fact = facts.get(position);


        holder.title.setText(fact.getTitle());
        holder.description.setText(fact.getDescription());
        Glide.with(context)
                .load(fact.getImageUrl())
                .into(holder.image);
    }


    @Override
    public int getItemCount() {
        return facts.size();
    }


    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;
        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.iv_food_fact);
            title = (TextView) view.findViewById(R.id.tv_food_fact_title);
            description = (TextView) view.findViewById(R.id.tv_food_fact_description);
        }
    }
}
