package com.example.diet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DirectionAdapter extends RecyclerView.Adapter<DirectionAdapter.ViewHolder> {
    private List<String> directions;

    public DirectionAdapter(List<String> directions) {
        this.directions = directions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_direction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String direction = directions.get(position);
        holder.textViewCount.setText(String.valueOf(position + 1));
        holder.textViewDirection.setText(direction);
    }

    @Override
    public int getItemCount() {
        return directions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCount;
        TextView textViewDirection;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewCount = itemView.findViewById(R.id.textViewCount);
            textViewDirection = itemView.findViewById(R.id.textViewDirection);
        }
    }
}
