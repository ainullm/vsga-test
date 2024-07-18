package com.example.vsga_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vsga_test.adapter.Adapter;
import com.example.vsga_test.helper.DbHelper;
import com.example.vsga_test.model.DataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadDataActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<DataModel> itemList = new ArrayList<DataModel>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NUMBER = "number";
    public static final String TAG_NAME = "name";
    public static final String TAG_BIRTH = "birth";
    public static final String TAG_GENDER = "gender";
    public static final String TAG_ADDRESS = "address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.read_data_activity);

        SQLite = new DbHelper(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);

        listView = (ListView) findViewById(R.id.list_view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadDataActivity.this, AddDataActivity.class);
                startActivity(intent);
            }
        });
        adapter = new Adapter(ReadDataActivity.this, itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getId();
                final String number = itemList.get(position).getNumber();
                final String name = itemList.get(position).getName();
                final String birth = itemList.get(position).getBirth();
                final String gender = itemList.get(position).getGender();
                final String address = itemList.get(position).getAddress();

                final CharSequence[] dialogItem = {"View", "Edit", "Delete"};
                dialog = new AlertDialog.Builder(ReadDataActivity.this);
                dialog.setCancelable(true);

                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent viewIntent = new Intent(ReadDataActivity.this, ViewDataActivity.class);
                                viewIntent.putExtra(TAG_ID, idx);
                                viewIntent.putExtra(TAG_NUMBER, number);
                                viewIntent.putExtra(TAG_NAME, name);
                                viewIntent.putExtra(TAG_BIRTH, birth);
                                viewIntent.putExtra(TAG_GENDER, gender);
                                viewIntent.putExtra(TAG_ADDRESS, address);
                                startActivity(viewIntent);
                                break;
                            case 1:
                                Intent intent = new Intent(ReadDataActivity.this, AddDataActivity.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NUMBER, number);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_BIRTH, birth);
                                intent.putExtra(TAG_GENDER, gender);
                                intent.putExtra(TAG_ADDRESS, address);
                                startActivity(intent);
                                break;
                            case 2:
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
            }
        });
        getAllData();
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String number = row.get(i).get(TAG_NUMBER);
            String name = row.get(i).get(TAG_NAME);
            String birth = row.get(i).get(TAG_BIRTH);
            String gender = row.get(i).get(TAG_GENDER);
            String address = row.get(i).get(TAG_ADDRESS);

            DataModel data = new DataModel();
            data.setId(id);
            data.setNumber(number);
            data.setName(name);
            data.setBirth(birth);
            data.setGender(gender);
            data.setAddress(address);

            itemList.add(data);
        }
        Log.d("", "item list:" + itemList);

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}
