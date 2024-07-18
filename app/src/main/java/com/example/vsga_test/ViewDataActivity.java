package com.example.vsga_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ViewDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Detail Data");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        TextView textViewNumber = findViewById(R.id.textViewNumber);
        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewBirth = findViewById(R.id.textViewBirth);
        TextView textViewGender = findViewById(R.id.textViewGender);
        TextView textViewAddress = findViewById(R.id.textViewAddress);

        Intent intent = getIntent();
        String id = intent.getStringExtra(ReadDataActivity.TAG_ID);
        String number = intent.getStringExtra(ReadDataActivity.TAG_NUMBER);
        String name = intent.getStringExtra(ReadDataActivity.TAG_NAME);
        String birth = intent.getStringExtra(ReadDataActivity.TAG_BIRTH);
        String gender = intent.getStringExtra(ReadDataActivity.TAG_GENDER);
        String address = intent.getStringExtra(ReadDataActivity.TAG_ADDRESS);

        textViewNumber.setText(number);
        textViewName.setText(name);
        textViewBirth.setText(birth);
        textViewGender.setText(gender);
        textViewAddress.setText(address);
    }
}
