package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Filters extends AppCompatActivity {
    ArrayList<ProductModel> productModels = new ArrayList<>();
ImageView burger,asian,pizza,mexican,donut;
    RecyclerView myRecyclerView;
    Product_RecyclerViewAdapter adapter;
    String[] productNames = {"Macdonalds","Pizza Hut","Arabiata","Papa Johns","Shear Box","Cheese lovers","Oriental Dish","Margerita"};
    String[] Prices = {"$4.99","2$","$4.99","$4.99","5$","6$","$4.99","2.5$"};
    String[] LongDesc = {"Bananas are nutritious. Bananas may be good for weight loss. apples may be good for your heart. As part of a healthful and varied diet.",
            "Red pepper can be used to cook sauce and many more recipes",
            "2 kilos of beef bones",
            "One and half kilo of pure chicken",
            "Bananas are nutritious. Bananas may be good for weight loss. apples may be good for your heart. As part of a healthful and varied diet.",
            "Red pepper can be used to cook sauce and many more recipes"," "," "};
    String[] productDesc = {"7pcs", "Priceg","0.5 kilo pepper","1kg, Priceg","1kg, Priceg","7pcs, Priceg","0.5 kilo pepper",""};
    String[] productTags = {"burger","pizza","asian","pizza","burger","pizza","asian","pizza"};
    int[] productImages = {R.drawable.mac,R.drawable.pizzahut,R.drawable.arabiata,
            R.drawable.papajohns,R.drawable.grand_share,R.drawable.pizzafood,R.drawable.spaghetti,R.drawable.margerita};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        GridLayoutManager layoutManager = new GridLayoutManager(Filters.this,2,GridLayoutManager.VERTICAL,false);
        myRecyclerView=findViewById(R.id.products_recycler_view);
         adapter=new Product_RecyclerViewAdapter(this,productModels);
        adapter.productNameTxt();
        burger=findViewById(R.id.imageView7);
        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burger.setBackgroundColor(Color.parseColor("#FE724C"));
               filter("burger"); }});
        asian=findViewById(R.id.imageView8);
        asian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burger.setBackgroundColor(Color.parseColor("#FE724C"));
                filter("asian"); }});
        pizza=findViewById(R.id.imageView9);
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burger.setBackgroundColor(Color.parseColor("#FE724C"));
                filter("pizza"); }});
        mexican=findViewById(R.id.imageView10);
        mexican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burger.setBackgroundColor(Color.parseColor("#FE724C"));
                filter("mexican"); }});

        donut=findViewById(R.id.imageView11);
        donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burger.setBackgroundColor(Color.parseColor("#FE724C"));
                filter("donut"); }});
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(layoutManager);
    }
void filter(String tag){
    for( int i=0;i<productNames.length;i++){

        if(productTags[i].equals(tag)){

            productModels.add(new ProductModel(Prices[i],productNames[i],productDesc[i],productImages[i],LongDesc[i],i));

            myRecyclerView.setAdapter(adapter);
        }
    }
}
}