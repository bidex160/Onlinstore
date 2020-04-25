package com.example.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CompletedOrder extends AppCompatActivity {
   cartrecycleadapter adapter;
    RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    DatabaseOpener databaseOpener;
    SQLiteDatabase database;
    Cursor cursor;
      ArrayList<dbitems> listcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order);
        initviews();
       listcart = new ArrayList<>();
        adapter = new cartrecycleadapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        compledorder compledorder = new compledorder();
        compledorder.execute();

    }

    private void initviews() {
        recyclerView = findViewById(R.id.completedre);
        bottomNavigationView = findViewById(R.id.completedbt);
    }
    public class compledorder extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.setDbitemsArrayList(listcart);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            databaseOpener = new DatabaseOpener(CompletedOrder.this);
            database =  databaseOpener.getReadableDatabase();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                cursor = database.query("CARTORDER",null,null,null, null,null,null);
                if (cursor.moveToFirst()) {
                    for (int i = 0; i < cursor.getCount(); i++) {

                        dbitems dbitems = new dbitems();
                        for (int j=0; j<cursor.getColumnCount(); j++){
                            switch (cursor.getColumnName(j)){
                                case "name":
                                    dbitems.setName(cursor.getString(j));

                                    break;
                                case "price":
                                   dbitems.setPrice(cursor.getInt(j));
                                    Log.e(TAG, "doInBackground: "+ cursor.getInt(j) );

                                    break;
                                case "sizes":
                                    //   q= cursor.getInt(j);
                                    dbitems.setSize(cursor.getString(j));

                                    break;
                                case "qt":
                                    //   q= cursor.getInt(j);
                                    dbitems.setQt(cursor.getInt(j));

                                    break;
                                case  "total":
                                    dbitems.setTotal(cursor.getInt(j));

                                    break;
                                case "imageuri":
                                    dbitems.setImageuri(cursor.getString(j));
                                    break;
                                case "description":
                                   dbitems.setDescription(cursor.getString(j));
                                    break;
                                default:
                                    break;
                            }
                        }

                       listcart.add(dbitems);
                        cursor.moveToNext();



                    }
                }

            }catch (Exception e){
                Log.e(TAG, "doInBackground: " + e );
            }
            return null;
        }
    }
}
