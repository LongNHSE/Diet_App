package com.example.diet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.Task;
import com.example.diet.ui.all_diet.AllDietActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;
    private Button testGoalSetupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.loginButton);
        testGoalSetupButton = findViewById(R.id.testGoalSetupButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.equals("huylong123") && password.equals("hello123")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userId", "6658350de9d4a6c7ae89854c");
                    editor.commit();

                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userId", "6658350de9d4a6c7ae89854c");
                    editor.commit();

                    startActivity(intent);
//                    showIncorrectCredentialsDialog();
                }
            }
        });

        testGoalSetupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GoalSetupActivity.class);
                startActivity(intent);
            }
        });


        TextView signUpTextView = findViewById(R.id.sign_up_text_view);
        String fullText = "Don’t have an account? Sign up";
        SpannableString spannableString = new SpannableString(fullText);

        ClickableSpan signUpClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Redirect to SignUpActivity
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        };

        // Set the color and clickable span for "Sign up"
        int signUpStartIndex = fullText.indexOf("Sign up");
        int signUpEndIndex = signUpStartIndex + "Sign up".length();
        spannableString.setSpan(signUpClickableSpan, signUpStartIndex, signUpEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.mineral_green)), signUpStartIndex, signUpEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the text color for the rest of the text
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)), 0, signUpStartIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Apply the spannable string to the text view
        signUpTextView.setText(spannableString);
        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void showIncorrectCredentialsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Login Failed")
                .setMessage("Password is incorrect, please try again.")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}