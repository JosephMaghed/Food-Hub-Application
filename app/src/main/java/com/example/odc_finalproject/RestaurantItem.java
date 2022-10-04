package com.example.odc_finalproject;

public class RestaurantItem {
    // Declaration of the variable
    private String ChildItemTitle;
    private int productImage;
    private int ItemPosition;

    // Constructor of the class
    // to initialize the variable*
    public RestaurantItem(String childItemTitle,int productImage,int ItemPosition)
    {
        this.ItemPosition = ItemPosition;
        this.ChildItemTitle = childItemTitle;
        this.productImage = productImage;
    }



    // Getter and Setter method
    // for the parameter
    public String getChildItemTitle()
    {
        return ChildItemTitle;
    }
    public int getChildItemImage()
    {
        return productImage;
    }
    public int getChildItemPosition() { return ItemPosition; }

    public void setChildItemTitle(
            String childItemTitle)
    {
        ChildItemTitle = childItemTitle;
    }
}
