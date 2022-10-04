package com.example.odc_finalproject;

public class ReviewModel {

    // description, tracks and duration, id.
    private String userName;
    private String review;
    private float rating;
    private int FOODID;
    //private String courseDescription;
    private int id;

    // creating getter and setter methods
    public String getUserName() {
        return userName;
    }

    public void setuserID(String userName) {
        this.userName = userName;
    }
//
public String getReview() {
    return review;
}

    public void setReview(String review) {
        this.review = review;
    }
    //
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    //
    public int getFOODID() {
        return FOODID;
    }

    public void setFOODID(int FOODID) {
        this.FOODID = FOODID;
    }


   /* public String getCourseDescription() {
        return courseDescription;
    }*/

  /*  public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public ReviewModel(String userName,String review,float rating ,int FOODID) {
        this.userName = userName;
        this.FOODID = FOODID;
        this.rating = rating;
        this.review = review;

    }
}