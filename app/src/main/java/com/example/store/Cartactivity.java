package com.example.store;
import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Cartactivity extends AppCompatActivity implements cartrecycleadapter.Passdelete{
    DatabaseOpener databaseOpener;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    TextView subtxt, setsubtxt, shiptxt, setshippintxt, tottxt, setotaltxt;
    Button procced;
    Cursor cursor;
    int total =0;
    String name;
    cartrecycleadapter adapter;
    ArrayList<dbitems> arrayList = new ArrayList<>();
    Toolbar toolbar;
  BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartactivity);
        initviews();
        setshippintxt.setText(String.valueOf(800));
        recyclerView.setLayoutManager(new LinearLayoutManager(Cartactivity.this));
        recyclerView.setAdapter(adapter);
      // setSupportActionBar(toolbar);
        Asynccart asynccart = new Asynccart();
        asynccart.execute();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.hom:
                    case R.id.serch:
                        Intent intent = new Intent(Cartactivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Cartf:
                        break;
                        default:
                            break;
                }
                return false;
            }
        });
        procced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(Cartactivity.this, Checkout.class);
                 intent.putExtra("name", name);
                startActivity(intent);

            }
        });

        try {

        }catch (Exception e){

        }
    }


    private void initviews() {
        recyclerView = findViewById(R.id.cartrecycle);
        subtxt = findViewById(R.id.subtotal);
        setsubtxt = findViewById(R.id.setsubtcart);
        shiptxt = findViewById(R.id.shippingtxt);
        setshippintxt = findViewById(R.id.setshipping);
        tottxt = findViewById(R.id.totaltxt);
        setotaltxt = findViewById(R.id.settotalcart);
        procced = findViewById(R.id.cartprocced);
        adapter = new cartrecycleadapter(this);
        //toolbar = findViewById(R.id.toolcheck);
        bottomNavigationView = findViewById(R.id.cartmenubt);

    }

    @Override
    public void passit(dbitems dbitems) {
        Log.e(TAG, "passit: "+ dbitems.toString() );
        if (arrayList != null){
            total=0;
            arrayList.clear();
        }

        databaseOpener.delete(sqLiteDatabase,"CART", dbitems.getId());

         Asynccart asynccart = new Asynccart();

        asynccart.execute();


    }

    @Override
    public void edittextchange(int oldqt, int nwqt, int oldprice) {
        Log.e(TAG, "edittextchange: "+ oldqt + nwqt + oldprice );
        if (arrayList != null){
            total=0;
            arrayList.clear();
        }

        try {
            databaseOpener.edit(sqLiteDatabase,"CART", oldqt, nwqt, oldprice, oldprice*nwqt);

        }catch (Exception e){

        }



        Asynccart asynccart = new Asynccart();
        asynccart.execute();

    }




    public class Asynccart extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            databaseOpener = new DatabaseOpener(Cartactivity.this);
            sqLiteDatabase = databaseOpener.getReadableDatabase();
            Log.e(TAG, "onPreExecute: cart" );
        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {

                cursor = sqLiteDatabase.query("CART",null,null,null, null,null,null);
                if (cursor.moveToFirst()) {
                    for (int i = 0; i < cursor.getCount(); i++) {

                        dbitems dbitems = new dbitems();
                        for (int j=0; j<cursor.getColumnCount(); j++){
                            switch (cursor.getColumnName(j)){
                                case "id":
                                    dbitems.setId(cursor.getInt(j));
                                    Log.e(TAG, "doInBackground:id " + cursor.getString(j) );

                                    break;
                                case "name":
                                    dbitems.setName(cursor.getString(j));
                                    Log.e(TAG, "doInBackground: " + cursor.getString(j) );
                                    break;
                                case "price":
                                    dbitems.setPrice(cursor.getInt(j));
                                    Log.e(TAG, "doInBackground: "+ cursor.getInt(j) );
                                  //  p=cursor.getInt(j);
                                    break;
                                case "qt":
                                    Log.e(TAG, "doInBackground: " + cursor.getInt(j));

                                    dbitems.setQt(cursor.getInt(j));

                                    break;
                                case  "total":
                                    dbitems.setTotal(cursor.getInt(j));
                                    total += cursor.getInt(j);
                                    Log.e(TAG, "doInBackground: " + cursor.getInt(j) );

                                    break;
                                case "imageuri":
                                    dbitems.setImageuri(cursor.getString(j));
                                    break;
                                case "description":
                                    dbitems.setDescription(cursor.getString(j));

                                case "sizes":
                                    dbitems.setSize(cursor.getString(j));
                                    break;
                                default:
                                    break;
                            }
                        }
                        arrayList.add(dbitems);
                        cursor.moveToNext();
                        name += "\n" +dbitems.toString();



                    }
                }

            }catch (Exception e){
                Log.e(TAG, "doInBackground: " + e );
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            setsubtxt.setText(String.valueOf(total));
            adapter.setDbitemsArrayList(arrayList);
            setotaltxt.setText(String.valueOf(total+800));


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        sqLiteDatabase.close();

    }
}
