package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.diet.auth.dto.SignUpRequest;
import com.example.diet.auth.dto.SignUpResponse;
import com.example.diet.auth.services.AuthService;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private PowerSpinnerView daySpinner, monthSpinner, yearSpinner, genderSpinner;
    private EditText usernameEditText, phoneEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEditText = findViewById(R.id.username_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);

        daySpinner = findViewById(R.id.day_spinner);
        monthSpinner = findViewById(R.id.month_spinner);
        yearSpinner = findViewById(R.id.year_spinner);
        genderSpinner = findViewById(R.id.gender_spinner);

        daySpinner.setItems(getDays());
        monthSpinner.setItems(getMonths());
        yearSpinner.setItems(getYears());
        genderSpinner.setItems(Arrays.asList(getResources().getStringArray(R.array.gender_array)));

        findViewById(R.id.sign_up_button).setOnClickListener(v -> signUpUser());

        setupBackButton();
        setupLogInTextView();
    }

    private void setupBackButton() {
        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());
    }

    private void setupLogInTextView() {
        TextView logInTextView = findViewById(R.id.log_in_text_view);
        String text = "Already have an account? Log in";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                navigateToLogIn();
            }
        };

        int startIndex = text.indexOf("Log in");
        int endIndex = startIndex + "Log in".length();

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.mineral_green)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        logInTextView.setText(spannableString);
        logInTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private List<String> getDays() {
        List<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        return days;
    }

    private List<String> getMonths() {
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        return months;
    }

    private List<String> getYears() {
        List<String> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1980; i <= currentYear; i++) {
            years.add(String.valueOf(i));
        }
        return years;
    }

    private boolean validateInputs(String username, String phone, String email, String password, String confirmPassword) {
        clearInputErrors();

        if (username.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid email format");
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void clearInputErrors() {
        usernameEditText.setError(null);
        phoneEditText.setError(null);
        emailEditText.setError(null);
        passwordEditText.setError(null);
        confirmPasswordEditText.setError(null);
    }

    private void signUpUser() {
        String username = usernameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (!validateInputs(username, phone, email, password, confirmPassword)) {
            return;
        }

        int day = daySpinner.getSelectedIndex() + 1;
        int month = monthSpinner.getSelectedIndex() + 1;
        int year = Integer.parseInt(yearSpinner.getText().toString());
        String dob = String.format("%04d-%02d-%02d", year, month, day);

        String gender = genderSpinner.getText().toString().toLowerCase();

        SignUpRequest request = new SignUpRequest(username, phone, dob, email, gender, password);

        // Log the request body
        Log.d("SignUpActivity", "SignUpRequest: " + new Gson().toJson(request));

        authService = RetrofitClient.getClient(null).create(AuthService.class);
        Call<ResponseDTO<SignUpResponse>> call = authService.signUpUser(request);

        call.enqueue(new Callback<ResponseDTO<SignUpResponse>>() {
            @Override
            public void onResponse(Call<ResponseDTO<SignUpResponse>> call, Response<ResponseDTO<SignUpResponse>> response) {
                Log.d("SignUpActivity", "Response received");

                if (response.isSuccessful() && response.body() != null) {
                    Log.d("SignUpActivity", "Response Body: " + new Gson().toJson(response.body()));
                    ResponseDTO<SignUpResponse> responseDTO = response.body();
                    if (responseDTO.getStatusCode() == 201) {
                        showSignUpSuccessDialog();
                    } else {
                        Toast.makeText(SignUpActivity.this, responseDTO.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorMessage = response.errorBody().string();
                        Log.d("SignUpActivity", "Response Error: " + errorMessage);
                        Gson gson = new Gson();
                        ResponseDTO errorResponse = gson.fromJson(errorMessage, ResponseDTO.class);
                        Toast.makeText(SignUpActivity.this, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(SignUpActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<SignUpResponse>> call, Throwable t) {
                Log.e("SignUpActivity", "onFailure: " + t.getMessage());
                Toast.makeText(SignUpActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSignUpSuccessDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Account Created")
                .setIcon(R.drawable.check_mark)
                .setMessage("Your account has been created successfully. Please log in to continue.")
                .setPositiveButton("Log In", (dialog, which) -> navigateToLogIn())
                .setCancelable(false)
                .show();
    }

    private void navigateToLogIn() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
