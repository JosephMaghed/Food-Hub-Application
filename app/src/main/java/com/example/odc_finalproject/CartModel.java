package com.example.odc_finalproject;

public class CartModel {
    // variables for our coursename,
    // description, tracks and duration, id.
    private int userID;
    private int TYPE;
    private int FOOD;
    //private String courseDescription;

    private int id;

    // creating getter and setter methods
    public int getuserID() {
        return userID;
    }

    public void setuserID(int userID) {
        this.userID = userID;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public int getFOOD() {
        return FOOD;
    }

    public void setFOOD(String courseTracks) {
        this.FOOD = FOOD;
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
    public CartModel(int TYPE, int FOOD, int userID) {
        this.userID = userID;
        this.TYPE = TYPE;
        this.FOOD = FOOD;

    }
}
