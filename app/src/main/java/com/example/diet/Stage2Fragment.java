package com.example.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class Stage2Fragment extends Fragment {

    private NumberPicker weightPickerInteger, weightPickerDecimal;
    private TextView liveWeightText;
    private double weight;
    private MaterialButton nextStepButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage2, container, false);

        weightPickerInteger = view.findViewById(R.id.weightPickerInteger);
        weightPickerDecimal = view.findViewById(R.id.weightPickerDecimal);
        liveWeightText = view.findViewById(R.id.liveWeightText);
        nextStepButton = getActivity().findViewById(R.id.btnNext);

        // Set up the integer weight picker
        weightPickerInteger.setMinValue(30);
        weightPickerInteger.setMaxValue(150);
        weightPickerInteger.setValue(75);

        // Set up the decimal weight picker
        weightPickerDecimal.setMinValue(0);
        weightPickerDecimal.setMaxValue(9);
        weightPickerDecimal.setValue(0);
        weightPickerDecimal.setFormatter(value -> String.valueOf(value));

        nextStepButton.setEnabled(false);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        weightPickerInteger.setOnValueChangedListener((picker, oldVal, newVal) -> updateWeight());
        weightPickerDecimal.setOnValueChangedListener((picker, oldVal, newVal) -> updateWeight());

        // Initialize live weight text
        updateWeight();

        return view;
    }

    private void updateWeight() {
        weight = weightPickerInteger.getValue() + weightPickerDecimal.getValue() / 10.0;
        liveWeightText.setText(String.format("%.1f kg", weight));
        nextStepButton.setEnabled(true);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));
    }

    public double getWeight() {
        return weight;
    }
}
