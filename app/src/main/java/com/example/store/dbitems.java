package com.example.store;

import android.os.Parcel;
import android.os.Parcelable;

public class dbitems implements Parcelable {
    int id;
    String name;
    int price;
    int qt;
    int total;
    String imageuri;
    String description;
    String size;

    public dbitems(int id,String name, int price, String size, int qt, int total, String imageuri, String description) {
        this.name = name;
        this.price = price;
        this.qt = qt;
        this.total = total;
        this.imageuri = imageuri;
        this.description = description;
        this.size = size;
        this.id = id;

    }

    public dbitems() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected dbitems(Parcel in) {
    }

    public static final Creator<dbitems> CREATOR = new Creator<dbitems>() {
        @Override
        public dbitems createFromParcel(Parcel in) {
            return new dbitems(in);
        }

        @Override
        public dbitems[] newArray(int size) {
            return new dbitems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override
    public String toString() {
        return "ProductDetails{" +"\n"+
                    "\t Name= '" + name + '\'' +"\n"+
                    "\t Price = " + price + "\n"+
                    "\t Quantity = " + qt + "\n"+
                    "\t Total = " + total + "\n"+
                    "\t imageuri = '" + imageuri + '\'' +"\n"+
                    "\t Description ='" + description + '\'' +
                    "\t Quamtities ='" + size + '\'' +
                '}'+"\n";
    }
}
