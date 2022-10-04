package com.example.odc_finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Product_RecyclerViewAdapter extends RecyclerView.Adapter<Product_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ProductModel> data;
    static String P_name;
    public static String productDesc;
    int[] productImages = {R.drawable.mac,R.drawable.pizzahut,R.drawable.arabiata,
            R.drawable.papajohns,R.drawable.grand_share,R.drawable.pizzafood,R.drawable.spaghetti,R.drawable.margerita};
    String[] productNames = {"Macdonalds","Pizza Hut","Arabiata","Papa Johns","Shear Box","Cheese lovers","Oriental Dish","Margerita"};
    public Product_RecyclerViewAdapter(Context context, ArrayList<ProductModel> data){
        this.context=context;
        this.data=data;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerItems=inflater.inflate(R.layout.product_recycler_view_cell,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(recyclerItems);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NotNull  MyViewHolder holder, int position) {
        holder.productNameTxt.setText(data.get(position).getProductName());
        holder.productDescTxt.setText(data.get(position).getProductDesc());
        holder.priceTxt.setText(data.get(position).getPrice());
        holder.productImageView.setImageResource(data.get(position).getProductImage());
        productDesc=data.get(position).getLongDescription();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, OrderDetails.class);
                i.putExtra("product", productNames[data.get(position).getFOOD()]);
                i.putExtra("image", productImages[data.get(position).getFOOD()]);
                i.putExtra("ItemPosition", data.get(position).getFOOD());
                context.startActivity(i);
            }
        });


    }



    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView productNameTxt;
        public TextView productDescTxt;
        public TextView priceTxt;
        public ImageView productImageView;
        public String desc;



        public MyViewHolder(@NotNull  View itemView) {
            super(itemView);

            this.productNameTxt = itemView.findViewById(R.id.product_name);
            this.productDescTxt = itemView.findViewById(R.id.product_desc);
            this.productImageView = itemView.findViewById(R.id.product_image);
            this.priceTxt = itemView.findViewById(R.id.price);
            this.desc=productDesc;





        }



    }
    public String productNameTxt(){

        return P_name;
    }
}
