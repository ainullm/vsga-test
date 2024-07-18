package com.example.vsga_test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin;
    private final String predefinedEmail = "test@gmail.com";
    private final String predefinedPassword = "password";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_activity);

        editEmail = findViewById(R.id.emailId);
        editPassword = findViewById(R.id.passwordId);
        btnLogin = findViewById(R.id.btnLogin);

        editEmail.setText(predefinedEmail);
        editPassword.setText(predefinedPassword);
        // Set up the login button click listener
        btnLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            if (validateCredentials(email, password)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateCredentials(String email, String password) {
        return email.equals(predefinedEmail) && password.equals(predefinedPassword);
    }
}
