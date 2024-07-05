package com.example.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class Stage1Fragment extends Fragment {

    private TextView optionLoseWeight, optionMaintainHealth, optionGainWeight;
    private String selectedGoal;
    private MaterialButton nextStepButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage1, container, false);

        optionLoseWeight = view.findViewById(R.id.optionLoseWeight);
        optionMaintainHealth = view.findViewById(R.id.optionMaintainHealth);
        optionGainWeight = view.findViewById(R.id.optionGainWeight);
        nextStepButton = getActivity().findViewById(R.id.btnNext);

        optionLoseWeight.setOnClickListener(v -> selectOption("Lose weight", optionLoseWeight));
        optionMaintainHealth.setOnClickListener(v -> selectOption("Maintain health", optionMaintainHealth));
        optionGainWeight.setOnClickListener(v -> selectOption("Gain weight", optionGainWeight));

        nextStepButton.setEnabled(false);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        return view;
    }

    private void selectOption(String goal, TextView selectedView) {
        selectedGoal = goal;
        nextStepButton.setEnabled(true);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));

        resetOptions();

        selectedView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.yellow_green));
        selectedView.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));
    }

    private void resetOptions() {
        optionLoseWeight.setBackgroundResource(R.drawable.unselected_background);
        optionLoseWeight.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        optionMaintainHealth.setBackgroundResource(R.drawable.unselected_background);
        optionMaintainHealth.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        optionGainWeight.setBackgroundResource(R.drawable.unselected_background);
        optionGainWeight.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
    }

    public String getSelectedGoal() {
        return selectedGoal;
    }
}
