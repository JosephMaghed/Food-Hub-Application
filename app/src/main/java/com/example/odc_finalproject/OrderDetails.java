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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {
    private DBHandler1 dbHandler;
    TextView PN,Desc,AVGReview;
    int imge;
    ImageView image,heart;
    Button AddReview;

    private ArrayList<ReviewModel> courseModalArrayList;
    RatingBar ratingBar;
    EditText ED_Review;

    private ReviewAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        AVGReview=findViewById(R.id.AVGReview);
        ratingBar=findViewById(R.id.ratinBar);
        ED_Review=findViewById(R.id.EDReview);
        ED_Review.requestFocus();
        float r=5;
        ratingBar.setRating(r);

        dbHandler = new DBHandler1(OrderDetails.this);
        PN=findViewById(R.id.ProductName);
        Desc=findViewById(R.id.AVGReview);
        image=findViewById(R.id.imageView);
        AddReview=findViewById(R.id.ADDREVIEWt);
        heart=findViewById(R.id.imageView7);

        Bundle b=new Bundle();
        b=getIntent().getExtras();
        String pName=b.getString("product","");
        String pDesc=b.getString("productDesc","");
        int Position=b.getInt("ItemPosition",0);
        String tes= String.valueOf(Position);

        imge = b.getInt("image");
        PN.setText(pName);
        Desc.setText(pDesc);
        image.setImageResource(imge);
        String AverageRating=" "+String.valueOf(dbHandler.Averagereview(Position));
        String subAVGrating=AverageRating.substring(0,3);
        AVGReview.setText(""+subAVGrating);
        AddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);
                float rating=ratingBar.getRating();
                String review =ED_Review.getText().toString();

                int Id=sharedPreferences.getInt("IDENTIFICATION",0);
                String userName=sharedPreferences.getString("name","");
               dbHandler.AddToReview(userName, Position, rating, review);


            }
        });



        courseModalArrayList = new ArrayList<>();




        // getting our course array
        // list from db handler class.
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);
        int Id=sharedPreferences.getInt("IDENTIFICATION",0);
        courseModalArrayList = dbHandler.readReviews(Position);

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new ReviewAdapter(courseModalArrayList, OrderDetails.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderDetails.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);


    }

}