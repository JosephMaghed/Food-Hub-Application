package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
EditText Name,Email,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Name=findViewById(R.id.fullName);
        Email=findViewById(R.id.E_mail);
        address=findViewById(R.id.address);

        SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);


        String name=sharedPreferences.getString("name","");
        int Id=sharedPreferences.getInt("IDENTIFICATION",0);

        Email.setText(sharedPreferences.getString("email",""));
        Name.setText(sharedPreferences.getString("name",""));
    }
}