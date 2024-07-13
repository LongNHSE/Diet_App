package com.example.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.diet.goal.dto.GoalResponse;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class Stage1Fragment extends Fragment {

    private LinearLayout goalOptionsContainer;
    private List<GoalResponse> goals;
    private MaterialButton nextStepButton;
    private EditText weightInput;
    private LinearLayout weightInputContainer;
    private GoalResponse selectedGoal;

    public Stage1Fragment(List<GoalResponse> goals) {
        this.goals = goals;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage1, container, false);

        goalOptionsContainer = view.findViewById(R.id.goalOptionsContainer);
        nextStepButton = getActivity().findViewById(R.id.btnNext);
        weightInput = view.findViewById(R.id.weightInput);
        weightInputContainer = view.findViewById(R.id.weightInputContainer);

        populateGoalOptions();

        nextStepButton.setEnabled(false);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        return view;
    }

    private void populateGoalOptions() {
        for (GoalResponse goal : goals) {
            TextView goalOption = new TextView(getContext());
            goalOption.setText(goal.getGoalName());
            goalOption.setPadding(10, 10, 10, 10);
            goalOption.setTextSize(16);
            goalOption.setGravity(View.TEXT_ALIGNMENT_CENTER);
            goalOption.setBackgroundResource(R.drawable.unselected_background);
            goalOption.setOnClickListener(v -> selectGoal(goal, goalOption));
            goalOptionsContainer.addView(goalOption);
        }
    }

    private void selectGoal(GoalResponse goal, TextView selectedView) {
        selectedGoal = goal;
        nextStepButton.setEnabled(true);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));

        resetGoalOptions();
        selectedView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.yellow_green));
        selectedView.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));

        weightInputContainer.setVisibility(goal.getSign() == 0 ? View.GONE : View.VISIBLE);
    }

    private void resetGoalOptions() {
        for (int i = 0; i < goalOptionsContainer.getChildCount(); i++) {
            View child = goalOptionsContainer.getChildAt(i);
            child.setBackgroundResource(R.drawable.unselected_background);
            ((TextView) child).setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        }
    }

    public GoalResponse getSelectedGoal() {
        if (selectedGoal.getSign() != 0 && !weightInput.getText().toString().isEmpty()) {
            int weightChange = Integer.parseInt(weightInput.getText().toString());
            selectedGoal.setSign(selectedGoal.getSign() * weightChange);
        }
        return selectedGoal;
    }
}
