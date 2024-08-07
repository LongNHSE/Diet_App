package com.example.diet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.diet.auth.dto.LoginRequest;
import com.example.diet.auth.dto.LoginResponse;
import com.example.diet.auth.services.AuthService;
import com.example.diet.bmi.dto.BMIResponse;
import com.example.diet.bmi.services.BMIService;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.loginButton);
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        loginButton.setOnClickListener(v -> loginUser());

        setupSignUpTextView();
    }

    private void setupSignUpTextView() {
        TextView signUpTextView = findViewById(R.id.sign_up_text_view);
        String text = "Don’t have an account? Sign up";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                navigateToSignUp();
            }
        };

        int startIndex = text.indexOf("Sign up");
        int endIndex = startIndex + "Sign up".length();

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.mineral_green)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        signUpTextView.setText(spannableString);
        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest loginRequest = new LoginRequest(email, password);

        AuthService authService = RetrofitClient.getClient(null).create(AuthService.class);
        Call<ResponseDTO<LoginResponse>> call = authService.loginUser(loginRequest);

        call.enqueue(new Callback<ResponseDTO<LoginResponse>>() {
            @Override
            public void onResponse(Call<ResponseDTO<LoginResponse>> call, Response<ResponseDTO<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    ResponseDTO<LoginResponse> responseDTO = response.body();
                    if (responseDTO != null && responseDTO.getStatusCode() == 200) {
                        LoginResponse loginResponse = responseDTO.getData();
                        saveUserData(loginResponse);
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        checkUserBMIAndNavigate(loginResponse);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed: " + responseDTO.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showIncorrectCredentialsDialog();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<LoginResponse>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserData(LoginResponse loginResponse) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", loginResponse.getToken());
        editor.putString("user", new Gson().toJson(loginResponse.getUser()));
        editor.putString("userId", loginResponse.getUser().get_id());
        editor.apply();
    }

    private void checkUserBMIAndNavigate(LoginResponse loginResponse) {
        BMIService bmiService = RetrofitClient.getClient(loginResponse.getToken()).create(BMIService.class);
        Call<ResponseDTO<List<BMIResponse>>> call = bmiService.getUserBMI();

        call.enqueue(new Callback<ResponseDTO<List<BMIResponse>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<BMIResponse>>> call, Response<ResponseDTO<List<BMIResponse>>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getData().isEmpty()) {
                    navigateToHome();
                } else {
                    navigateToBMISetupActivity(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<BMIResponse>>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error checking BMI: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToBMISetupActivity(LoginResponse loginResponse) {
        Intent intent = new Intent(LoginActivity.this, BMISetupActivity.class);
        intent.putExtra("userId", loginResponse.getUser().get_id());
        intent.putExtra("token", loginResponse.getToken());
        startActivity(intent);
        finish();
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void showIncorrectCredentialsDialog() {
        Toast.makeText(this, "Login failed: Incorrect username or password", Toast.LENGTH_SHORT).show();
    }
}
