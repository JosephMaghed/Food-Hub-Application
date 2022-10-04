package com.example.odc_finalproject;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {

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
            R.drawable.papajohns,R.drawable.grand_share,R.drawable.pizza,R.drawable.spaghetti,R.drawable.margerita};

    private List<ChildItem> ChildItemList;
    Context context;

    // Constructor
    ChildItemAdapter(Context context,List<ChildItem> childItemList)
    {
        this.context=context;
        this.ChildItemList = childItemList;
    }

    @NotNull
    @Override
    public ChildViewHolder onCreateViewHolder(
            @NotNull ViewGroup viewGroup,
            int i)
    {

        // Here we inflate the corresponding
        // layout of the child item
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.child_item,
                        viewGroup, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ChildItemAdapter.ChildViewHolder childViewHolder ,
            int position)
    {

        // Create an instance of the ChildItem
        // class for the given position
        ChildItem childItem
                = ChildItemList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        childViewHolder
                .ChildItemTitle
                .setText(childItem.getChildItemTitle());
        childViewHolder
                .ChildItemImage
                .setImageResource(childItem.getChildItemImage());

        childViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                if(childItem.getChildType()==1){
                 i=new Intent(context, ProductDetails.class);}
                else if(childItem.getChildType()==0){
                     i=new Intent(context, RestDetails.class);
                }
                i.putExtra("product", childItem.getChildItemTitle());
                i.putExtra("image", childItem.getChildItemImage());
                i.putExtra("ItemPosition", childItem.getChildItemPosition());
                i.putExtra("PRICE",Prices[ childItem.getChildItemPosition()]);
                i.putExtra("productDesc",LongDesc[ childItem.getChildItemPosition()]);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount()
    {

        // This method returns the number
        // of items we have added
        // in the ChildItemList
        // i.e. the number of instances
        // of the ChildItemList
        // that have been created
        return ChildItemList.size();
    }

    // This class is to initialize
    // the Views present
    // in the child RecyclerView
    class ChildViewHolder
            extends RecyclerView.ViewHolder {

        TextView ChildItemTitle;
        ImageView ChildItemImage;

        ChildViewHolder(View itemView)
        {
            super(itemView);
            ChildItemTitle
                    = itemView.findViewById(
                    R.id.child_item_title);
            ChildItemImage
                    = itemView.findViewById(
                    R.id.img_child_item);
        }
    }
}