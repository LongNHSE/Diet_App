package com.example.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class Stage2Fragment extends Fragment {

    private NumberPicker weightPicker;
    private double weight;
    private MaterialButton nextStepButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage2, container, false);

        weightPicker = view.findViewById(R.id.weightPicker);
        nextStepButton = getActivity().findViewById(R.id.btnNext);

        nextStepButton.setEnabled(false);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        weightPicker.setMinValue(30);
        weightPicker.setMaxValue(150);
        weightPicker.setValue(70);
        weightPicker.setWrapSelectorWheel(true);
        weightPicker.setFormatter(value -> String.format("%02d kg", value));
        weightPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            weight = newVal;
            nextStepButton.setEnabled(true);
            nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));
        });

        return view;
    }

    public double getWeight() {
        return weight;
    }
}
