package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the back arrow click listener
        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back navigation
                onBackPressed();
            }
        });

        // Set up the log in text view with clickable "Log in" text
        TextView logInTextView = findViewById(R.id.log_in_text_view);
        String fullText = "Already have an account? Log in";
        SpannableString spannableString = new SpannableString(fullText);

        // Create a clickable span for "Log in"
        ClickableSpan logInClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Redirect to LoginActivity
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };

        // Set the color and clickable span for "Log in"
        int logInStartIndex = fullText.indexOf("Log in");
        int logInEndIndex = logInStartIndex + "Log in".length();
        spannableString.setSpan(logInClickableSpan, logInStartIndex, logInEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.mineral_green)), logInStartIndex, logInEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the text color for the rest of the text
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)), 0, logInStartIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Apply the spannable string to the text view
        logInTextView.setText(spannableString);
        logInTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}