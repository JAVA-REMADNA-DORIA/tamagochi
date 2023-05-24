package com.example.tamagopersona;

public class AllItem {

    private String name;
    private double price;
    private String imgName;
    private int quantity;

    public AllItem(String name, double price, String imgName, int quantity) {
        this.name = name;
        this.price= price;
        this.imgName = imgName;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getImgName() {
        return this.imgName;
    }

    public int getQuantity(){
        return this.quantity;
    }
}