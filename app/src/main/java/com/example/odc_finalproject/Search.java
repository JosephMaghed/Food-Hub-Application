package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
EditText EDSeach;
Button BTNSearch;
Switch switchbtn;
    String searchResult;
    ArrayList<ProductModel> productModels = new ArrayList<>();

    RecyclerView myRecyclerView,myRecyclerView2;

    String[] productNames = {"Macdonalds","Pizza Hut","Arabiata","Papa Johns","Shear Box","Cheese lovers","Oriental Dish","Margerita"};
    String[] Prices = {"$4.99","2$","$4.99","$4.99","5$","6$","$4.99","2.5$"};
    String[] LongDesc = {"Bananas are nutritious. Bananas may be good for weight loss. apples may be good for your heart. As part of a healthful and varied diet.",
            "Red pepper can be used to cook sauce and many more recipes",
            "2 kilos of beef bones",
            "One and half kilo of pure chicken",
            "Bananas are nutritious. Bananas may be good for weight loss. apples may be good for your heart. As part of a healthful and varied diet.",
            "Red pepper can be used to cook sauce and many more recipes"," "," "};
    String[] productDesc = {"7pcs, Priceg","0.5 kilo pepper","1kg, Priceg","1kg, Priceg","7pcs, Priceg","0.5 kilo pepper"};

    int[] productImages = {R.drawable.mac,R.drawable.pizzahut,R.drawable.arabiata,
            R.drawable.papajohns,R.drawable.grand_share,R.drawable.pizzafood,R.drawable.spaghetti,R.drawable.margerita};
   // Product_RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        EDSeach=findViewById(R.id.SearchBox);
        GridLayoutManager layoutManager = new GridLayoutManager(Search.this,2,GridLayoutManager.VERTICAL,false);
        myRecyclerView=findViewById(R.id.products_recycler_view);
        Product_RecyclerViewAdapter adapter=new Product_RecyclerViewAdapter(this,productModels);
        adapter.productNameTxt();
        BTNSearch=findViewById(R.id.button5);
        switchbtn=findViewById(R.id.switch1);
        switchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchbtn.isChecked()){
                    switchbtn.setText("Restaurants");


                }else{
                    switchbtn.setText("Food");

                }
            }
        });

        BTNSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Search=EDSeach.getText().toString();
                int j=4;
                int num=0;

                if(switchbtn.isChecked()){
                    switchbtn.setText("Restaurants");
                    j=0;
                    num=-4;

                }else{
                    switchbtn.setText("Food");
                     j=4;
                     num=0;
                }
                switchbtn.setTextOff("Food");
                for( int i=j;i<productNames.length+num;i++){

                    if(productNames[i].equals(Search)){

                        productModels.add(new ProductModel(Prices[i],productNames[i],productDesc[i],productImages[i],LongDesc[i+1],i));

                        myRecyclerView.setAdapter(adapter);
                    }
                }

                  }


              });

       // setupProductModel();
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(layoutManager);







    }
    public void setupProductModel(){
        for(int i=0;i<productNames.length;i++){
            productModels.add(new ProductModel(Prices[i],productNames[i],productDesc[i],productImages[i],LongDesc[i+1],i));
        }


    }


}