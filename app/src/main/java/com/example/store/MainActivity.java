package com.example.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
DatabaseOpener sqlite;
SQLiteDatabase database;

      Recycleadapterclass recycleadapterclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();

//Async async = new Async();
//async.execute();
   //    setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new MainFragment());
        transaction.commit();


        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initviews() {
        navigationView = findViewById(R.id.navigation);
       drawerLayout = findViewById(R.id.drawers);
     //   toolbar = findViewById(R.id.toolb);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.cart:
                break;
            case R.id.location:
                break;
            case R.id.about:
                break;
            case R.id.terms:
                break;
            case R.id.licence:
                break;
            case R.id.cartorder:
                Log.e(TAG, "onNavigationItemSelected: comppp" );

                Intent intent = new Intent(MainActivity.this, CompletedOrder.class);
                startActivity(intent);
                break;
            case R.id.addnew:
                addtodatabase();

                break;

                default:
                    break;


        }
        return false;
    }

    private void addtodatabase() {
        Intent intent = new Intent(MainActivity.this, AddData.class);
        startActivity(intent);

    }
    public class Async extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            sqlite = new DatabaseOpener(MainActivity.this);
            database = sqlite.getReadableDatabase();

            Log.e(TAG, "onPreExecute: " );
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
