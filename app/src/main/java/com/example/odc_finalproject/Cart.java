package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private ArrayList<CartModel> courseModalArrayList;
    private DBHandler1 dbHandler;
    private CartAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    TextView Ed;
    Button ADDtoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Ed=findViewById(R.id.textView30);
        ADDtoCart=findViewById(R.id.Order);

// initializing our all variables.

        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler1(Cart.this);



        // getting our course array
        // list from db handler class.
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);
        int Id=sharedPreferences.getInt("IDENTIFICATION",0);
        courseModalArrayList = dbHandler.readCart( Id);

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new CartAdapter(courseModalArrayList, Cart.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Cart.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
        ADDtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.deleteFromCart(Id);
               Intent i = new Intent(getApplicationContext(),Orders.class);
                startActivity(i);

            }
        });
    }

}
