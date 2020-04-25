package com.example.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Checkout extends AppCompatActivity {
Button bt;
DatabaseOpener databaseOpener;
SQLiteDatabase database;
Cursor cursor;
List<dbitems> list = new ArrayList<>();;
String name;
EditText clientname, clientcontact, clientaddress, clientemail;
TextView nametxt, emailttx, contacttxt, delivertxt;
String Message="";
String Cmessage="";
int number1=0;
Toolbar toolbar;

boolean tryresult, postresult;
    String string;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
   //     setSupportActionBar(toolbar);
       // arrayList = new ArrayList<>();
       number1 = (int)(Math.random() * 10000);
        Intent intent = getIntent();
         string = intent.getStringExtra("name");
        Log.e(TAG, "onCreate: "+ string );

        initviews();


         background background = new background();
        background.execute();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendmessage();





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
                        Intent intenth = new Intent(Checkout.this, MainActivity.class);
                        startActivity(intenth);
                        break;
                    case R.id.Cartf:
                        databaseOpener.createcart(database);
                       background background1 = new background();
                       background1.execute();
                        Log.e(TAG, "onNavigationItemSelected: cart" );
                        Intent intent1 = new Intent(Checkout.this, Cartactivity.class);
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });



    }

    private void sendmessage() {
        String cmail="";
        String cname="";
        String caddress= "";
        String ccontact="";
        String csubject = "SicoCosmetics";
        try {
            String email = "sicocosmetics3@gmail.com";
            String subject = "Online Order";

            cname = clientname.getText().toString();
            cmail = clientemail.getText().toString();
            ccontact = clientcontact.getText().toString();
            caddress = clientaddress.getText().toString();



            Message = "Customer Name:-" +cname + "\n" +"Customer Email:-"+ cmail + "\n" +"Customer Contact:-"+ccontact +"\n" +"Delivery Address:-" +caddress +"\n"+"Order No:-"+ String.valueOf(number1) +"\n" + string;

            sendmail sendmail = new sendmail(Checkout.this, email, subject,Message);
            sendmail.execute();

        }catch (Exception e){

        }




        Cmessage = "Hi\t" + cname + "," + "\n" +"\n" + "Just to let you know - we've received your"+"\n" +"order and its being processed"+ "\n" +"\n"+
                "**Please Note that Payment on Delivery is only" +"\n" +"available for orders in lagos."
                + "\n" + " Account Number :- 0519389891" +"\n"+"Account Name :- Balogun Isiak"+"\n"+"GTB"+"\n" + "Order No:-\t"+ number1 + "\n" +"\n" +  "\n" +"\n" +"Contact :-" + ccontact+"\n" +"\n" +  "Delivery Address:- \t" + caddress;
        Cmessage.toUpperCase();
        if (cmail != null){
            Log.e(TAG, "sendmessage: "+ Cmessage );
            ClientMail clientMail = new ClientMail(Checkout.this, cmail, csubject, Cmessage );
            clientMail.execute();

        }



        AlertDialog.Builder builder = new AlertDialog.Builder(Checkout.this)
                .setTitle("Succesfully")

                .setMessage("Your Order haa been Received!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(Checkout.this, MainActivity.class);
                        startActivity(intent);
                        databaseOpener.deletecart(database);
                        background background = new background();
                        background.execute();


                    }
                });
          builder.show();






    }

    private void initviews() {
        nametxt = findViewById(R.id.usernametxt);
        emailttx = findViewById(R.id.useremailtxt);
        contacttxt = findViewById(R.id.usernumber);
        delivertxt = findViewById(R.id.useraddresttx);

        clientaddress = findViewById(R.id.useraddresedit);
        clientcontact = findViewById(R.id.usernumedit);
        clientname = findViewById(R.id.usernamedit);
        clientemail = findViewById(R.id.usermailedit);
        bt = findViewById(R.id.button);
       // toolbar  = findViewById(R.id.toolcheck);
        bottomNavigationView = findViewById(R.id.billingmenu);
    }




    public class background extends AsyncTask<Void,Void,Void>{
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        databaseOpener = new DatabaseOpener(Checkout.this);
        database = databaseOpener.getReadableDatabase();
        databaseOpener.cartorder(database);
        databaseOpener.createcart(database);
    }

    @Override
    protected Void doInBackground(Void... voids) {


            try {

                cursor = database.query("CART",null,null,null, null,null,null);
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

                     databaseOpener.orderinser(database, dbitems);
                        cursor.moveToNext();
                        name += "\n" +dbitems.toString();



                    }
                }

            }catch (Exception e){
                Log.e(TAG, "doInBackground: " + e );
            }



        return null;
    }
}
}
