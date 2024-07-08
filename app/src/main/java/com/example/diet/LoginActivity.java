package com.example.diet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diet.auth.dto.LoginRequest;
import com.example.diet.auth.dto.LoginResponse;
import com.example.diet.auth.services.AuthService;
import com.example.diet.response.ResponseDTO;
import com.example.diet.user.dto.User;
import com.example.diet.util.RetrofitClient;
import com.google.gson.Gson;

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        TextView signUpTextView = findViewById(R.id.sign_up_text_view);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });
    }

    private void loginUser() {
        String username = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        LoginRequest loginRequest = new LoginRequest(username, password);

        AuthService authService = RetrofitClient.getClient(null).create(AuthService.class);
        Call<ResponseDTO<LoginResponse>> call = authService.loginUser(loginRequest);

        call.enqueue(new Callback<ResponseDTO<LoginResponse>>() {
            @Override
            public void onResponse(Call<ResponseDTO<LoginResponse>> call, Response<ResponseDTO<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    ResponseDTO<LoginResponse> responseDTO = response.body();
                    if (responseDTO != null && responseDTO.getStatusCode() == 200) {
                        saveUserData(responseDTO.getData());
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        navigateToMainActivity();
                    } else {
                        showIncorrectCredentialsDialog();
                    }
                } else {
                    showIncorrectCredentialsDialog();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<LoginResponse>> call, Throwable t) {
                Log.e("LoginActivity", "onFailure: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserData(LoginResponse loginResponse) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("refreshToken", loginResponse.getRefreshToken());
        editor.putString("user", new Gson().toJson(loginResponse.getUser()));
        editor.putString("userId", loginResponse.getUser().get_id());
        editor.apply();

        // Check and log stored user data
//        checkStoredUserData();
    }

    private void checkStoredUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String refreshToken = sharedPreferences.getString("refreshToken", null);
        String userJson = sharedPreferences.getString("user", null);
        String userId = sharedPreferences.getString("userId", null);

        if (refreshToken != null && userJson != null && userId != null) {
            User user = new Gson().fromJson(userJson, User.class);
            Log.d("LoginActivity", "Refresh Token: " + refreshToken);
            Log.d("LoginActivity", "User ID: " + userId);
            Log.d("LoginActivity", "User: " + user.toString());
            Toast.makeText(this, "User data stored successfully!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No user data found.", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
