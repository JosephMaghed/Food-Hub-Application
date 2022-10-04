package com.example.odc_finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {


    int[] productImages = {R.drawable.pizzafood,R.drawable.tacos,R.drawable.spaghetti,
            R.drawable.burger,R.drawable.burger,R.drawable.burger,R.drawable.pizzafood,R.drawable.tacos,R.drawable.spaghetti,
            R.drawable.burger,R.drawable.burger,R.drawable.burger};
    // variable for our array list and context
    private ArrayList<ReviewModel> courseModalArrayList;
    private Context context;

    // constructor
    public ReviewAdapter(ArrayList<ReviewModel> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.modal.getuserID()
        ReviewModel modal = courseModalArrayList.get(position);
        holder.UserName.setText(modal.getUserName());
        holder.Rating.setText(String.valueOf(modal.getRating()));
        holder.Review.setText(modal.getReview());
       // holder.courseDurationTV.setImageResource(productImages[modal.getFOOD()]);


    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView UserName, Rating, Review;
        private ImageView courseDurationTV;

        public ViewHolder(@NotNull View itemView) {
            super(itemView);
            // initializing our text views
            UserName = itemView.findViewById(R.id.Uname);
            Rating = itemView.findViewById(R.id.rating);
            Review = itemView.findViewById(R.id.review);

        }
    }
}