package com.example.odc_finalproject;

public class ProductModel {
    private String productName;
    private String ProductDesc;
    private String Price;
    private String LongDescription;
    private int productImage;
    private int FOOD;
    ProductModel(String Price,String ProductModel,String ProductDesc,int productImage,String LongDescription,int FOOD){
        this.productName=ProductModel;
        this.ProductDesc=ProductDesc;
        this.productImage=productImage;
        this.LongDescription=LongDescription;
        this.Price=Price;
        this.FOOD=FOOD;
    }
    public String getProductName() {
        return productName;
    }
    public String getPrice() {
        return Price;
    }
    public String getProductDesc(){
        return ProductDesc;
    }
    public String getLongDescription() {
        return LongDescription;
    }
    public int getFOOD() {
        return FOOD;
    }

    public int getProductImage() {
        return productImage;
    }
}
