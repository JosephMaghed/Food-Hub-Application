package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    private ArrayList<CartModel> courseModalArrayList;
    private DBHandler1 dbHandler;
    private CartAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    TextView Ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        Ed=findViewById(R.id.textView30);


// initializing our all variables.

        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler1(Orders.this);



        // getting our course array
        // list from db handler class.
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);
        int Id=sharedPreferences.getInt("IDENTIFICATION",0);
        courseModalArrayList = dbHandler.readOrders( Id);

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new CartAdapter(courseModalArrayList, Orders.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Orders.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);

    }

}
