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
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Stage4Fragment extends Fragment {

    private PowerSpinnerView daySpinner, monthSpinner, yearSpinner;
    private TextView liveDateText;
    private Calendar calendar;
    private MaterialButton nextStepButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage4, container, false);

        daySpinner = view.findViewById(R.id.daySpinner);
        monthSpinner = view.findViewById(R.id.monthSpinner);
        yearSpinner = view.findViewById(R.id.yearSpinner);
        liveDateText = view.findViewById(R.id.liveDateText);
        nextStepButton = getActivity().findViewById(R.id.btnNext);

        calendar = Calendar.getInstance();

        // Set up day spinner
        List<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        daySpinner.setItems(days);

        // Set up month spinner
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        monthSpinner.setItems(months);

        // Set up year spinner
        int currentYear = calendar.get(Calendar.YEAR);
        List<String> years = new ArrayList<>();
        for (int i = 1900; i <= currentYear; i++) {
            years.add(String.valueOf(i));
        }
        yearSpinner.setItems(years);

        // Set initial values
        daySpinner.selectItemByIndex(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        monthSpinner.selectItemByIndex(calendar.get(Calendar.MONTH));
        yearSpinner.selectItemByIndex(currentYear - 1900);

        nextStepButton.setEnabled(false);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        daySpinner.setOnSpinnerItemSelectedListener((i, o, i1, t1) -> updateDate());
        monthSpinner.setOnSpinnerItemSelectedListener((i, o, i1, t1) -> updateDate());
        yearSpinner.setOnSpinnerItemSelectedListener((i, o, i1, t1) -> updateDate());

        // Initialize live date text
        updateDate();

        return view;
    }

    private void updateDate() {
        int day = Integer.parseInt(daySpinner.getText().toString());
        int month = Integer.parseInt(monthSpinner.getText().toString());
        int year = Integer.parseInt(yearSpinner.getText().toString());

        calendar.set(year, month - 1, day);  // Month is 0-based in Calendar

        // Ensure the date is valid
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (day > maxDay) {
            daySpinner.selectItemByIndex(maxDay - 1);
            day = maxDay;
        }

        liveDateText.setText(String.format("%02d/%02d/%d", day, month, year));
        nextStepButton.setEnabled(true);
        nextStepButton.setTextColor(ContextCompat.getColor(getContext(), R.color.mineral_green));
    }

    public Calendar getDateOfBirth() {
        return calendar;
    }
}
