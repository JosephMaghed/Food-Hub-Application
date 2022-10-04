package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RestDetails extends AppCompatActivity {
    private DBHandler1 dbHandler;
    TextView PN,Desc,PRICE,ED_Review;
    int imge;
    ImageView image,heart;
    Button AddtoCart,ADDToFavorite,AddReview;
    Intent intent;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_details);
        PRICE=findViewById(R.id.PRICE);
        dbHandler = new DBHandler1(RestDetails.this);
        PN=findViewById(R.id.ProductName);
        Desc=findViewById(R.id.AVGReview);
        image=findViewById(R.id.imageView);
        ratingBar=findViewById(R.id.ratinBar);
        ED_Review=findViewById(R.id.EDReview);
        float r=5;
        ratingBar.setRating(r);
        heart=findViewById(R.id.imageView7);
        AddReview=findViewById(R.id.ADDREVIEWt);

        Bundle b=new Bundle();
        b=getIntent().getExtras();
        String pName=b.getString("product","");
        String pDesc=b.getString("productDesc","");
        String pPrice=b.getString("PRICE","");
        int Position=b.getInt("ItemPosition",0);
        String tes= String.valueOf(Position);
        ADDToFavorite=findViewById(R.id.Favorite);
        imge = b.getInt("image");
        PRICE.setText(pPrice);
        PN.setText(pName);
        Desc.setText(pDesc);
        image.setImageResource(imge);
        ADDToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);

                int Id=sharedPreferences.getInt("IDENTIFICATION",0);
                int type;
                if (Position>3){
                    type=0;//0 for food
                }
                else{
                    type=1;
                }
                dbHandler.AddToFavorite(Id,Position,type);

            }
        });
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
    }
}

