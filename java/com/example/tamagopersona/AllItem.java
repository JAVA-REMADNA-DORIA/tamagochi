package com.example.tamagopersona;

public class AllItem {

    private String name;
    private double price;
    private String imgName;
    private int quantity;
    private String description;

    public AllItem(String name, double price, String imgName, int quantity, String description) {
        this.name = name;
        this.price= price;
        this.imgName = imgName;
        this.quantity = quantity;
        this.description = description;
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

    public String getDescription() {
        return this.description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}