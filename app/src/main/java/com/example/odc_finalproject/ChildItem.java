package com.example.odc_finalproject;

public class ChildItem {

    // Declaration of the variable
    private String ChildItemTitle;
    private int productImage;
    private int ItemPosition;
private int type;
    // Constructor of the class
    // to initialize the variable*
    public ChildItem(String childItemTitle,int productImage,int ItemPosition,int type)
    {
        this.ItemPosition = ItemPosition;
        this.ChildItemTitle = childItemTitle;
        this.productImage = productImage;
        this.type = type;
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
    public int getChildType() { return type; }
    public void setChildItemTitle(
            String childItemTitle)
    {
        ChildItemTitle = childItemTitle;
    }
}
