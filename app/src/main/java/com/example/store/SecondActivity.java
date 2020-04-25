package com.example.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AtomicFile;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SecondActivity extends AppCompatActivity {

    TextView ptxt, totxt, qtxt, ntxt, sptxt, sntxt, stotxt, qtt, descptxt, spt, secol, cqt;

    ImageView imgreview;
    Button addcartbt, viewcartbt;
    int price=0;
    int qt =1;
 boolean[] checkeditems;
    TabLayout tabLayout;
    ViewPager pager;
    DatabaseOpener databaseOpener;
    SQLiteDatabase database;
    String imageuri, imageuri1;
    String desc;
    String name;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    Spinner spinner;

    ArrayList<Integer> useritem;
    String[] listitems;
    pageradapter padapter;


    List<String> quantity;
    List<String> url = new ArrayList<>();
    String cquant = "";
    int count;
  int te=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initviews();

        useritem = new ArrayList<>();

        padapter = new pageradapter(getSupportFragmentManager());
        pager.setAdapter(padapter);
        tabLayout.setupWithViewPager(pager);
          ArrayAdapter<String> spadapter  = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quantity);
          spinner.setAdapter(spadapter);
      Asyndata asyndata = new Asyndata();
        asyndata.execute();

        Intent intent = getIntent();
         name  = intent.getStringExtra("name");
          price = intent.getIntExtra("price", 0);
         desc = intent.getStringExtra("description");
        imageuri = intent.getStringExtra("imageuri");
        imageuri1 = intent.getStringExtra("imageuri1");
        url.add(imageuri);
        url.add(imageuri1);
        Log.e(TAG, "onCreate: "+ price );
        try {
           ArrayList<fragment>  fragmentArrayList = new ArrayList<>();
           for (int i=0; i<url.size(); i++){
                fragment fragment = new fragment();
                Bundle bundle = new Bundle();
                bundle.putString("imageuri", url.get(i));
                fragment.setArguments(bundle);
            Log.e(TAG, "onCreate: " + url.get(i));
                fragmentArrayList.add(fragment);
           }
            padapter.setFragmentArrayList(fragmentArrayList);

        }catch (Exception e){

        }
        sntxt.setText(name);
        sptxt.setText(String.valueOf(price));
        qtt.setText(String.valueOf(price * qt));
        descptxt.setText(desc);

        Log.e(TAG, "onCreate: "+ name );

        if (name.toLowerCase().contains("Zikel tube".toLowerCase())){
            listitems = getResources().getStringArray(R.array.Zikel_tube);


        }else if(name.toLowerCase().contains("Zikel Powder".toLowerCase())){
            listitems = getResources().getStringArray(R.array.Zaron_Powder);

            Log.e(TAG, "onCreate: powder" );
        }else if (name.toLowerCase().contains("Zikel foundation Bottle".toLowerCase())){

            listitems = getResources().getStringArray(R.array.Zikel_bottle);
        }else if (name.toLowerCase().contains("Flawless")){
           listitems = getResources().getStringArray(R.array.Flawless);


        }else if (name.toLowerCase().contains("Loreal True Match".toLowerCase())){


            listitems = getResources().getStringArray(R.array.Lorealtrue_foundation);
        }else if (name.toLowerCase().contains("Eyelashes".toLowerCase())){


            listitems = getResources().getStringArray(R.array.Eyelashes);


        }else if (name.toLowerCase().contains("Davis Pencil".toLowerCase())){

            listitems = getResources().getStringArray(R.array.Davis_Pencil);



        }else if (name.toLowerCase().contains("Set of Brush".toLowerCase())){
            listitems = getResources().getStringArray(R.array.SET_OF_Brushes);

        }else if (name.toLowerCase().contains("Vessie Goldsmith foundation".toLowerCase())){

            listitems = getResources().getStringArray(R.array.Vessie_Goldsmith_Foundation);


        }else if(name.toLowerCase().contains("L A Girl Powder".toLowerCase())){

            listitems = getResources().getStringArray(R.array.LAGirl_powder);
        }else if(name.toLowerCase().contains("Jordana Foundation".toLowerCase())){

            listitems = getResources().getStringArray(R.array.Jordana_Foundation);

        }else if(name.toLowerCase().contains("Mac Foundation".toLowerCase())){
            listitems = getResources().getStringArray(R.array.Mac_foundation);


        }else if(name.toLowerCase().contains("Jordana Black".toLowerCase())){

            listitems = getResources().getStringArray(R.array.JordanaBC_powder);

        }else if(name.toLowerCase().contains("Jordana Silver".toLowerCase())){

            listitems = getResources().getStringArray(R.array.JordanSc_powder);


        }else if(name.toLowerCase().contains("Fina Foundation".toLowerCase())){

            listitems = getResources().getStringArray(R.array.fina_foundation);

        }else if(name.toLowerCase().contains("Zaron Powder".toLowerCase())){
            listitems = getResources().getStringArray(R.array.Zaron_Powder);


        }else if(name.toLowerCase().contains("Zaron Foundation".toLowerCase())){
            listitems = getResources().getStringArray(R.array.Zaron_Foundation);

        }else if(name.toLowerCase().contains("Milani Powder Black".toLowerCase())){
            listitems = getResources().getStringArray(R.array.MilaniPower_BC);


        }else if(name.toLowerCase().contains("Milani Powder".toLowerCase())){

            listitems = getResources().getStringArray(R.array.Milani_Powder);


        }else if(name.toLowerCase().contains("Vee beauty Foundation".toLowerCase())){

            listitems = getResources().getStringArray(R.array.VeeBeauty_Foundation);


        }else if(name.toLowerCase().replaceAll("[\\n\\t ]", "").contains("SuperStay foundation".toLowerCase().replaceAll("[\\n\\t ]",""))){
            listitems = getResources().getStringArray(R.array.SS_foundation);


        }else if(name.toLowerCase().contains("FitMe Foundation".toLowerCase())){

            listitems = getResources().getStringArray(R.array.fitme_foundation);



        }else if(name.toLowerCase().contains("NYX Foundation".toLowerCase())){
         //   listitems = getResources().getStringArray(R.array.N);

        }else{

           listitems = getResources().getStringArray(R.array.others);

        }


        checkeditems = new boolean[listitems.length];
        secol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: " );

                AlertDialog.Builder mbuilder = new AlertDialog.Builder(SecondActivity.this);
                mbuilder.setTitle("Select Colors");
                mbuilder.setMultiChoiceItems(listitems, checkeditems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b){
                            if (!useritem.contains(i)){
                                useritem.add(i);
                            }else {
                                useritem.remove(i);
                            }
                        }else {
                            useritem.remove(i);
                        }


                    }
                });
                mbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String item="";
                        for (int k=0; k<useritem.size(); k++){
                           item = item + listitems[useritem.get(k)];
                            if (k != useritem.size()-1){
                                item = item + ",";
                            }
                        }
                        Log.e(TAG, "onClick: " + item );
                        secol.setText(item);
                        qtt.setText(String.valueOf(price * useritem.size()));
                        cqt.setText("1 *" + (useritem.size()));

                    }
                });
                 mbuilder.show();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               switch (i){
                   case 0:
                       Log.e(TAG, "onItemSelected: case0" );

                       break;
                   case 1:
                       count = 0;

                       cquant = "";
                       count = 0;

                       qtt.setText(String.valueOf(price * useritem.size()));

                       cqt.setText("1" );

                       spinner.setSelection(0);
                       break;
                   case 2:
                       cquant = cquant + ","+ "1";
                       count += 1;
                       te++;
                       cqt.setText(cquant);
                       qtt.setText(String.valueOf(price * count));
                       Log.e(TAG, "onItemSelected: "+ i);
                       spinner.setSelection(0);
                       break;
                   case 3:

                       count += 2;
                       cquant = cquant +","+ "2";
                       cqt.setText(cquant);
                       qtt.setText(String.valueOf(price * count));
                       Log.e(TAG, "onItemSelected: "+ i );
                       spinner.setSelection(0);
                       break;
                   case 4:
                       count += 3;
                       cquant = cquant + ","+ "3";
                       Log.e(TAG, "onItemSelected: "+ i);
                       cqt.setText(cquant);
                       qtt.setText(String.valueOf(price * count));
                       spinner.setSelection(0);
                       break;
                   case 5:
                       count += 4;
                       cquant = cquant+","+ "4";
                       Log.e(TAG, "onItemSelected: "+ i );
                       qtt.setText(String.valueOf(price * count));
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                   case 6:
                       count += 5;
                       cquant = cquant+","+ "5";
                       Log.e(TAG, "onItemSelected: "+ i );
                       qtt.setText(String.valueOf(price * count));
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                   case 7:
                       count += 6;
                       cquant = cquant+","+ "6";
                       Log.e(TAG, "onItemSelected: "+ i );
                       qtt.setText(String.valueOf(price * count));
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                   case 8:
                       count += 7;
                       cquant = cquant+","+ "7";
                       qtt.setText(String.valueOf(price * count));
                       Log.e(TAG, "onItemSelected: "+ i);
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                   case 9:
                       count += 8;
                       cquant = cquant+","+ "8";
                       qtt.setText(String.valueOf(price * count));
                       Log.e(TAG, "onItemSelected: "+ i);
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                   case 10:
                       count += 9;
                       cquant = cquant+","+ "9";
                       qtt.setText(String.valueOf(price * count));
                       Log.e(TAG, "onItemSelected: "+ i );
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                   case 11:

                       count += 10;
                       cquant = cquant + "," + "10";
                       Log.e(TAG, "onItemSelected: "+ i );
                       qtt.setText(String.valueOf(price * count));
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                   case 12:
                       count += 11;
                       cquant = cquant+","+ "11";
                       Log.e(TAG, "onItemSelected: "+ i );
                       qtt.setText(String.valueOf(price * count));
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                   case 13:
                       count += 12;
                       cquant = cquant+","+ "12";
                       Log.e(TAG, "onItemSelected: "+ i);
                       qtt.setText(String.valueOf(price * count));
                       cqt.setText(cquant);
                       spinner.setSelection(0);
                       break;
                       default:
                           Log.e(TAG, "onItemSelected:dd "+ i);
                           break;
               }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.e(TAG, "onNothingSelected: " );

            }
        });

         addcartbt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 try {
                     databaseOpener.createcart(database);
                     Asyndata asyndata1 = new Asyndata();
                     asyndata1.execute();
                     if (count == 0 && useritem.size() >=1){
                         Log.e(TAG, "onClick: 0" + count);

                         databaseOpener.insert(database, "CART",imageuri, name, price, desc, cquant, useritem.size(), price*useritem.size());
                     }else if (useritem.size()<1 && count==0){
                         Log.e(TAG, "onClick:1 " + count );

                         databaseOpener.insert(database, "CART",imageuri, name, price, desc, cquant, 1, price*1);

                     }else {
                         Log.e(TAG, "onClick: 2" + count );
                         databaseOpener.insert(database, "CART",imageuri, name, price, desc, cquant, count, price * count);

                     }


                     Toast.makeText(SecondActivity.this, "Added Succesfuly", Toast.LENGTH_LONG).show();
                     Log.e(TAG, "onClick: " + imageuri + name + price + desc + qt + price*count );


                 }catch (Exception e){
                     Toast.makeText(SecondActivity.this, String.valueOf(e), Toast.LENGTH_LONG).show();
                 }

             }
         });

  viewcartbt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent intent1 = new Intent(SecondActivity.this, Cartactivity.class);
          startActivity(intent1);


      }
  });
  bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
          switch (menuItem.getItemId()){
              case R.id.serch:
                  Log.e(TAG, "onNavigationItemSelected: searc" );
                  break;
              case R.id.hom:
                 Intent intenth = new Intent(SecondActivity.this, MainActivity.class);
                 startActivity(intenth);
                  break;
              case R.id.Cartf:
                  databaseOpener.createcart(database);
                  Asyndata asyndata1 = new Asyndata();
                  asyndata1.execute();
                  Log.e(TAG, "onNavigationItemSelected: cart" );
                  Intent intent1 = new Intent(SecondActivity.this, Cartactivity.class);
                  startActivity(intent1);
                  break;
                  default:
                      break;
          }
          return false;
      }
  });


    }


    private void initviews() {
        ptxt = findViewById(R.id.pview);
        totxt = findViewById(R.id.totalviw);
        qtxt = findViewById(R.id.qview);
        ntxt = findViewById(R.id.nview);
        sptxt = findViewById(R.id.pset);
        sntxt = findViewById(R.id.nset);
        stotxt = findViewById(R.id.qview);
        descptxt = findViewById(R.id.descsecond);
//       toolbar = findViewById(R.id.toobrs);
        cqt = findViewById(R.id.cqt);
          qtt = findViewById(R.id.stotoal);

        imgreview = findViewById(R.id.picimg);
        spt = findViewById(R.id.sptxt);
        secol = findViewById(R.id.txtt);

       addcartbt = findViewById(R.id.addtocartbt);
       viewcartbt = findViewById(R.id.viewcartbt);
       bottomNavigationView= findViewById(R.id.secondmenu);
       tabLayout = findViewById(R.id.tblayou);
       pager = findViewById(R.id.pager);

        quantity = new ArrayList<>();
     spinner = findViewById(R.id.spp);
  //   int y = Integer.parseInt("A");
        quantity.add("Qty");
        quantity.add("0");
        quantity.add("1");
        quantity.add("2");
        quantity.add("3");
        quantity.add("4");
        quantity.add("5");
        quantity.add("6");
        quantity.add("7");
        quantity.add("8");
        quantity.add("9");
        quantity.add("10");
        quantity.add("11");
        quantity.add("12");





    }



    public class Asyndata extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            databaseOpener = new DatabaseOpener(SecondActivity.this);
            database = databaseOpener.getReadableDatabase();
            Log.e(TAG, "onPreExecute: " );

        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
