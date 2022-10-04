package com.example.odc_finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetails extends AppCompatActivity {
    private DBHandler1 dbHandler;
    TextView PN,Desc,PRICE;
    int imge;
    ImageView image,heart;
    Button AddtoCart,ADDToFavorite;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        PRICE=findViewById(R.id.PRICE);
        dbHandler = new DBHandler1(ProductDetails.this);
        PN=findViewById(R.id.ProductName);
        Desc=findViewById(R.id.AVGReview);
        image=findViewById(R.id.imageView);
        AddtoCart=findViewById(R.id.ADDREVIEWt);
        ADDToFavorite=findViewById(R.id.Favorite);
        heart=findViewById(R.id.imageView7);

        Bundle b=new Bundle();
        b=getIntent().getExtras();
        String pName=b.getString("product","");
        String pDesc=b.getString("productDesc","");
        String pPrice=b.getString("PRICE","");
        int Position=b.getInt("ItemPosition",0);
        String tes= String.valueOf(Position);

        imge = b.getInt("image");
        PRICE.setText(pPrice);
        PN.setText(pName);
        Desc.setText(pDesc);
        image.setImageResource(imge);
        AddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);

                int Id=sharedPreferences.getInt("IDENTIFICATION",0);
                dbHandler.AddToCart(Id,Position,8);

            }
        });
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

    }
}