package com.example.vsga_test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vsga_test.helper.DbHelper;

public class AddDataActivity extends AppCompatActivity {

    EditText textId, textNumber, textName, textBirth, textGender, textAddress;
    Button btnSubmit, btnCancel;

    String id, number, name, birth, gender, address;

    DbHelper SQLite = new DbHelper(this);


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.add_data_activity);

        textId = (EditText) findViewById(R.id.textId);
        if (textId == null) {
            Log.e("AddDataActivity", "textId EditText is null");
            // Handle the error or return to prevent further execution
        }
        textNumber = (EditText) findViewById(R.id.numberId);
        textName = (EditText) findViewById(R.id.nameId);
        textBirth = (EditText) findViewById(R.id.birthId);
        textGender = (EditText) findViewById(R.id.genderId);
        textAddress = (EditText) findViewById(R.id.addressId);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        id = getIntent().getStringExtra(ReadDataActivity.TAG_ID);
        number = getIntent().getStringExtra(ReadDataActivity.TAG_NUMBER);
        name = getIntent().getStringExtra(ReadDataActivity.TAG_NAME);
        birth = getIntent().getStringExtra(ReadDataActivity.TAG_BIRTH);
        gender = getIntent().getStringExtra(ReadDataActivity.TAG_GENDER);
        address = getIntent().getStringExtra(ReadDataActivity.TAG_ADDRESS);


        if (id == null || id.equals("")) {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            textId.setText(id);
            textNumber.setText(number);
            textName.setText(name);
            textBirth.setText(birth);
            textGender.setText(gender);
            textAddress.setText(address);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (textId != null && textId.getText().toString().equals("")){
                        save();
                    }else {
                        edit();
                    }
                }catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void blank(){
        textNumber.requestFocus();
        textId.setText(null);
        textNumber.setText(null);
        textName.setText(null);
        textBirth.setText(null);
        textGender.setText(null);
        textAddress.setText(null);
    }

    private void save() {
        if (String.valueOf(textNumber.getText()).equals(null) || String.valueOf(textNumber.getText()).equals("") ||
                String.valueOf(textName.getText()).equals(null) || String.valueOf(textName.getText()).equals("") ||
                String.valueOf(textBirth.getText()).equals(null) || String.valueOf(textBirth.getText()).equals("") ||
                String.valueOf(textGender.getText()).equals(null) || String.valueOf(textGender.getText()).equals("") ||
                String.valueOf(textAddress.getText()).equals(null) || String.valueOf(textAddress.getText()).equals("")) {
            Log.d("", "All fields must be filled");
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(textNumber.getText().toString().trim(), textName.getText().toString().trim(), textBirth.getText().toString().trim(), textGender.getText().toString().trim(), textAddress.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(textNumber.getText()).equals(null) || String.valueOf(textNumber.getText()).equals("") ||
                String.valueOf(textName.getText()).equals(null) || String.valueOf(textName.getText()).equals("") ||
                String.valueOf(textBirth.getText()).equals(null) || String.valueOf(textBirth.getText()).equals("") ||
                String.valueOf(textGender.getText()).equals(null) || String.valueOf(textGender.getText()).equals("") ||
                String.valueOf(textAddress.getText()).equals(null) || String.valueOf(textAddress.getText()).equals("")) {
            Log.d("", "All fields must be filled");
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(textId.getText().toString().trim()), textNumber.getText().toString().trim(), textName.getText().toString().trim(), textBirth.getText().toString().trim(), textGender.getText().toString().trim(), textAddress.getText().toString().trim());
            blank();
            finish();
        }
    }
}
