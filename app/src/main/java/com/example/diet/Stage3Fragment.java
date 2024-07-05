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

public class Stage3Fragment extends Fragment {

    private NumberPicker heightPicker;
    private int height;
    private MaterialButton nextStepButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage3, container, false);

        heightPicker = view.findViewById(R.id.heightPicker);
        nextStepButton = getActivity().findViewById(R.id.btnNext);

        nextStepButton.setEnabled(false);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        heightPicker.setMinValue(100);
        heightPicker.setMaxValue(250);
        heightPicker.setValue(170);
        heightPicker.setWrapSelectorWheel(true);
        heightPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            height = newVal;
            nextStepButton.setEnabled(true);
            nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));
        });

        return view;
    }

    public int getHeight() {
        return height;
    }
}
