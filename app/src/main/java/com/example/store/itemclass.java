package com.example.store;

import android.os.Build;

import java.util.ArrayList;
import java.util.Objects;

public class itemclass {
    String imageuri;
    String imageuri1;
    String name;
    int price;
    String description;


    itemclass(){

    }

    public itemclass(String imageuri, String imageuri1, String name, int price, String description) {
        this.imageuri = imageuri;
        this.imageuri1 = imageuri1;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getImageuri1() {
        return imageuri1;
    }

    public void setImageuri1(String imageuri1) {
        this.imageuri1 = imageuri1;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
