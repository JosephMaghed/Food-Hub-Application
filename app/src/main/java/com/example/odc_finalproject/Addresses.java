package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Addresses extends AppCompatActivity {
    private ArrayList<AdressModel> courseModalArrayList;
    private DBHandler1 dbHandler;
    private AdressAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    EditText EDAddress;
    Button AddAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresses);
        AddAddress=findViewById(R.id.ADDAdress);
        EDAddress=findViewById(R.id.edAdress);
        // initializing our all variables.

        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler1(Addresses.this);



        // getting our course array
        // list from db handler class.
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);
        int Id=sharedPreferences.getInt("IDENTIFICATION",0);
        courseModalArrayList = dbHandler.readAdress( Id);

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new AdressAdapter(courseModalArrayList, Addresses.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Addresses.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
        AddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHandler.ADD_ADDRESS(Id,EDAddress.getText().toString());
                Intent i = new Intent(getApplicationContext(),Addresses.class);
                startActivity(i);
            }
        });

    }

}