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

public class Stage3Fragment extends Fragment {

    private NumberPicker heightPicker;
    private TextView liveHeightText;
    private int height;
    private MaterialButton nextStepButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage3, container, false);

        heightPicker = view.findViewById(R.id.heightPicker);
        liveHeightText = view.findViewById(R.id.liveHeightText);
        nextStepButton = getActivity().findViewById(R.id.btnNext);

        // Set up the height picker
        heightPicker.setMinValue(100);
        heightPicker.setMaxValue(200);
        heightPicker.setValue(175);

        nextStepButton.setEnabled(false);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        heightPicker.setOnValueChangedListener((picker, oldVal, newVal) -> updateHeight());

        // Initialize live height text
        updateHeight();

        return view;
    }

    private void updateHeight() {
        height = heightPicker.getValue();
        liveHeightText.setText(String.format("%d cm", height));
        nextStepButton.setEnabled(true);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));
    }

    public int getHeight() {
        return height;
    }
}
