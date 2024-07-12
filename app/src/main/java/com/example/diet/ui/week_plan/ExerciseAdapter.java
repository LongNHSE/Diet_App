package com.example.diet.ui.week_plan;
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
import com.example.diet.exercise.dto.service.dto.Exercise;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private Context context;
    private List<Exercise> exerciseList;

    public ExerciseAdapter(Context context, List<Exercise> exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_exercise_detail, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.exName.setText( "Exercise: " + exercise.getExName());
        holder.calorexp.setText(String.valueOf(exercise.getCalorexp()) + " kcal");
        holder.description.setText(exercise.getDescription());
        Glide.with(context).load(exercise.getIcon()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        TextView exName, calorexp, description;
        ImageView icon;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exName = itemView.findViewById(R.id.exercise_name);
            calorexp = itemView.findViewById(R.id.exercise_calories);
            description = itemView.findViewById(R.id.exercise_description);
            icon = itemView.findViewById(R.id.exerciseImage);
        }
    }
}
