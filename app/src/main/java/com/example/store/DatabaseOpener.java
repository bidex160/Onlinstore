package com.example.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DatabaseOpener extends SQLiteOpenHelper {
     private static final int dbversion = 1;
     private static  final String dbname = "CLIENT DETAILS";

    public DatabaseOpener(@Nullable Context context) {
        super(context, dbname, null, dbversion);
        Log.e(TAG, "DatabaseOpener: " );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: db" );

        String sqlcm = "CREATE TABLE IF NOT EXISTS CART (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, price INTEGER,  sizes TEXT, qt INTEGER, total INTEGER, imageuri TEXT, description TEXT);";
        String cartorder = "CREATE TABLE IF NOT EXISTS CARTORDER (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, price INTEGER, sizes TEXT, qt INTEGER, total INTEGER, imageuri TEXT, description TEXT);";
            db.execSQL(sqlcm);
            db.execSQL(cartorder);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert (SQLiteDatabase db,String tbname, String imageuri, String name, int price, String description, String sizes,int qt, int total){

        ContentValues contentValues = new ContentValues();
        contentValues.put("imageuri", imageuri);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("description", description);
        contentValues.put("qt", qt);
        contentValues.put("total", total);
        contentValues.put("sizes", sizes);

        db.insert(tbname,null, contentValues);

    }
    public void cartorder(SQLiteDatabase database){
        String cartorder = "CREATE TABLE IF NOT EXISTS CARTORDER (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, price INTEGER, sizes TEXT, qt INTEGER, total INTEGER, imageuri TEXT, description TEXT);";

        database.execSQL(cartorder);

    }
    public void orderinser(SQLiteDatabase db, dbitems dbitems){
        ContentValues contentValues = new ContentValues();
        contentValues.put("imageuri", dbitems.getImageuri());
        contentValues.put("name", dbitems.getName());
        contentValues.put("price", dbitems.getPrice());
        contentValues.put("description", dbitems.getDescription());
        contentValues.put("qt", dbitems.getQt());
        contentValues.put("total", dbitems.getTotal());
        contentValues.put("sizes", dbitems.getSize());

        db.insert("CARTORDER",null, contentValues);


    }
public void createcart(SQLiteDatabase db){

    String sqlcm = "CREATE TABLE IF NOT EXISTS CART (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, price INTEGER, sizes TEXT,qt INTEGER, total INTEGER, imageuri TEXT, description TEXT);";



    db.execSQL(sqlcm);

}

public void edit(SQLiteDatabase db ,String tbname, int oldqt, int qt, int oldtotal, int total){
       ContentValues contentValues = new ContentValues();
       contentValues.put("qt", qt);

    ContentValues contentValues1 = new ContentValues();
    contentValues1.put("total", total);

       db.update(tbname, contentValues, "qt=?", new String[] {String.valueOf(oldqt)});

       db.update(tbname, contentValues1, "total=?", new String[] {String.valueOf(oldtotal)});

}
public void deletecart(SQLiteDatabase db){
        String dcart = "drop table if exists " + "CART";
        db.execSQL(dcart);
}

public void delete(SQLiteDatabase db, String tbname, int id){

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        db.delete(tbname, "id=?", new String[] {String.valueOf(id)});
}

public void createan(SQLiteDatabase db){
    String sql2 = "CREATE TABLE IF NOT EXISTS allproduct (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, price INTEGER,  imageuri TEXT, description TEXT);";
    db.execSQL(sql2);

}
public void inserallproduct(SQLiteDatabase db, String name, int price, String imageuri, String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("imageuri", imageuri);
        contentValues.put("description", description);
        db.insert("allproduct", null, contentValues);


}


public void deleteallptabel(SQLiteDatabase db){

      String d = "drop table if exists " + "allproduct";
        db.execSQL(d);
}

}
