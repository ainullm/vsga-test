package com.example.vsga_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnReadData, btnInputData, btnInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnReadData = findViewById(R.id.btnReadData);
        btnInputData = findViewById(R.id.btnInputData);
        btnInformation = findViewById(R.id.btnInformation);

        btnReadData.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReadDataActivity.class);
            startActivity(intent);
        });

        btnInputData.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
            startActivity(intent);
        });

        btnInformation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InformationActivity.class);
            startActivity(intent);
        });
    }
}