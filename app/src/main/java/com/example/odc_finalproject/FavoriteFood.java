package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriteFood extends AppCompatActivity {
    private ArrayList<CartModel> courseModalArrayList;
    private DBHandler1 dbHandler;
    private CartAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    TextView Ed;

    Switch switchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_food);
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler1(FavoriteFood.this);

        SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);
        int Id=sharedPreferences.getInt("IDENTIFICATION",0);
        switchbtn=findViewById(R.id.switch2);
        switchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int type=0;
                if(switchbtn.isChecked()){
                    switchbtn.setText("Restaurants");
                    type=1;
                    courseModalArrayList = dbHandler.readFavorite(Id,type);
                    courseRVAdapter = new CartAdapter(courseModalArrayList, FavoriteFood.this);
                    coursesRV = findViewById(R.id.idRVCourses);

                    // setting layout manager for our recycler view.
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavoriteFood.this, RecyclerView.VERTICAL, false);
                    coursesRV.setLayoutManager(linearLayoutManager);

                    // setting our adapter to recycler view.
                    coursesRV.setAdapter(courseRVAdapter);


                }else{
                    switchbtn.setText("Food");
                    type=0;
                    courseModalArrayList = dbHandler.readFavorite(Id,type);
                    courseRVAdapter = new CartAdapter(courseModalArrayList, FavoriteFood.this);
                    coursesRV = findViewById(R.id.idRVCourses);

                    // setting layout manager for our recycler view.
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavoriteFood.this, RecyclerView.VERTICAL, false);
                    coursesRV.setLayoutManager(linearLayoutManager);

                    // setting our adapter to recycler view.
                    coursesRV.setAdapter(courseRVAdapter);
                }
            }
        });

        // getting our course array
        // list from db handler class.



        // on below line passing our array lost to our adapter class.

    }
}