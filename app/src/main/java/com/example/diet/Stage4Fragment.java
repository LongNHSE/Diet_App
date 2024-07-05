package com.example.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class Stage4Fragment extends Fragment {

    private DatePicker datePicker;
    private Calendar dateOfBirth;
    private MaterialButton completeButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage4, container, false);

        datePicker = view.findViewById(R.id.datePicker);
        completeButton = getActivity().findViewById(R.id.btnNext);

        completeButton.setEnabled(false);
        completeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        datePicker.setOnDateChangedListener((view1, year, monthOfYear, dayOfMonth) -> {
            dateOfBirth = Calendar.getInstance();
            dateOfBirth.set(year, monthOfYear, dayOfMonth);
            completeButton.setEnabled(true);
            completeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));
        });

        return view;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }
}
