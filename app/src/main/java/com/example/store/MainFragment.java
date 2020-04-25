package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainFragment extends Fragment {
    TextView textView;
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;

    DatabaseReference databaseReference;
    Recycleadapterclass adapter;

     ArrayList<itemclass> itemclasses;
    ArrayList<itemclass> powder;

     SQLiteDatabase sqLiteDatabase;
     DatabaseOpener databaseOpener;


     TextView filter;
     Spinner spinner;
    List<String> spinnerarray;
    EditText editsearch;
    Button btsearch;
    String gettxtt;
    Cursor cursor;


  int count =0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

       Aysncallproducts aysncallproducts = new Aysncallproducts();
        aysncallproducts.execute();
       itemclasses = new ArrayList<>();
       powder = new ArrayList<>();

       spinnerarray = new ArrayList<>();

        initview(view);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_spinner_dropdown_item, spinnerarray
        );
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        allproducts();
                        break;
                    case 1:
                      powderpallete();
                        break;
                    case 2:
                        eyeshadow();
                        break;
                    case 3:
                        foundation();
                        break;
                    case 4:
                    //    Brushes();
                        break;
                    case 5:
                     //   Makeupbox();
                        break;
                    case 6:
                    //    Lippallete();
                        break;
                    case 7:
                     //   Lipstick();
                        break;
                    case 8:
                    //    Compact();
                        break;
                    case 9:
                //     EyelMethod();
                        break;
                    case 10:
                        //RingLight();
                        break;
                    case 11:
                     //   Spray();
                    case 12:
                    //    Othersmethod();
                    default:

                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              if (i2 != 0){
                  String x= editsearch.getText().toString();
                  gettxtt = x;
                // Aysncallproducts aysncallproducts1  = new Aysncallproducts();
                  //aysncallproducts.execute();
                  if (gettxtt != null){
                      if (itemclasses != null) {
                          itemclasses.clear();

                      }
                  }

                  try {
                      String name = "eye";
                      String query = "SELECT * FROM allproduct WHERE name LIKE \"%" + gettxtt + "%\"";
                      // cursor = sqLiteDatabase.query("allproduct",null,null,null, null,null,null);
                      cursor = sqLiteDatabase.rawQuery(query, null);
                      if (cursor.moveToFirst()) {
                          for (int j = 0; j < cursor.getCount(); j++) {

                              itemclass itemclass = new itemclass();
                              for (int k=0; k<cursor.getColumnCount(); k++){
                                  switch (cursor.getColumnName(k)){
                                      case "name":
                                          itemclass.setName(cursor.getString(k));
                                          Log.e(TAG, "doInBackground: " + cursor.getString(k) );
                                          break;
                                      case "price":
                                          itemclass.setPrice(cursor.getInt(k));
                                          break;

                                      case "imageuri":
                                    //      itemclass.setImageuri(cursor.getString(k));
                                          break;
                                      case "description":
                                          itemclass.setDescription(cursor.getString(k));
                                          break;
                                      default:
                                          break;
                                  }
                              }
                              itemclasses.add(itemclass);
                              cursor.moveToNext();


                          }



                      }

                      adapter = new Recycleadapterclass(itemclasses, getActivity());
                      recyclerView.setAdapter(adapter);

                  }catch (Exception e){
                      Log.e(TAG, "doInBackground: " + e );
                  }












              }


            }

            @Override
            public void afterTextChanged(Editable editable) {

                  //  Log.e(TAG, "afterTextChanged: " + gettxtt);



            }
        });


  GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
  layoutManager.setReverseLayout(true);
  //layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.serch:

                        Log.e(TAG, "onNavigationItemSelected: "+ "seaerch" );
                        break;
                    case R.id.Cartf:
                        Intent intent = new Intent(getActivity(), Cartactivity.class);
                        startActivity(intent);
                        break;
                    case R.id.hom:
                        break;
                    default:
                        break;


                }
                return false;
            }
        });


        return view;

    }
/*
    private void Othersmethod() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Others");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                   String price =  dataSnapshot1.child("price").getValue().toString();
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                    itemclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void Spray() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Spray");
    }

    private void RingLight() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("RingLight");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                   String price =  dataSnapshot1.child("price").getValue().toString();
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                    itemclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void EyelMethod() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Eyelashes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                    String price =  dataSnapshot1.child("price").getValue().toString();
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                    itemclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void Compact() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Compact");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                    String price = dataSnapshot1.child("price").getValue().toString();
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                    itemclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void Lipstick() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Lipsticks");
    }

    private void Lippallete() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Lip Pallete");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                    String price = dataSnapshot1.child("price").getValue().toString();
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                    itemclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void Makeupbox() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Make Up BOX");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                    String price =  dataSnapshot1.child("price").getValue().toString();
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                    itemclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void Brushes() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Brushes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                    String price =  dataSnapshot1.child("price").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                    itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                    itemclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });
    }
*/
    private void foundation() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Foundation");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                    String price = dataSnapshot1.child("price").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                   // itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                    //temclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void eyeshadow() {
        if (itemclasses != null) {
            itemclasses.clear();

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Powder Pallete");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                    String price =  dataSnapshot1.child("price").getValue().toString();
                    int priceint = Integer.parseInt(price);
                  //  String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                 //   itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
                  //  itemclasses.add(itemclass);


                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });


    }
  private void powderpallete() {
       if (itemclasses != null){
           itemclasses.clear();

       }
        // Log.e(TAG, "powderpallete: " + itemclasses.size() );
  databaseReference = FirebaseDatabase.getInstance().getReference("Powder Pallete");
  databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


              String name  =dataSnapshot1.child("name").getValue().toString();
              String desc = dataSnapshot1.child("description").getValue().toString();
               String price =  dataSnapshot1.child("price").getValue().toString();
              int priceint = Integer.parseInt(price);
              String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
           //   itemclass itemclass = new itemclass(imageuri, name,priceint,desc);
             // itemclasses.add(itemclass);


          }

          adapter = new Recycleadapterclass(itemclasses, getActivity());
          recyclerView.setAdapter(adapter);

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
          Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

      }
  });
    }



    private void allproducts() {
        Aysncallproducts aysncallproducts = new Aysncallproducts();
        aysncallproducts.execute();
        databaseOpener.createan(sqLiteDatabase);

        databaseReference = FirebaseDatabase.getInstance().getReference("sicostore");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Aysncallproducts aysncallproducts = new Aysncallproducts();
                aysncallproducts.execute();
                if (itemclasses != null){
                    itemclasses.clear();

                }

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String name  =dataSnapshot1.child("name").getValue().toString();
                    String desc = dataSnapshot1.child("description").getValue().toString();
                    String price =  dataSnapshot1.child("price").getValue().toString();
                    int priceint = Integer.parseInt(price);
                    String imageuri = dataSnapshot1.child("imageuri").getValue().toString();
                    String imageuri1 = dataSnapshot1.child("imageuri1").getValue().toString();

                    itemclass itemclass = new itemclass(imageuri,imageuri1,name,priceint,desc);
                          Log.e(TAG, "onDataChange: " + imageuri);
                 Log.e(TAG, "onDataChange: "+imageuri1 );

                    itemclasses.add(itemclass);
                   databaseOpener.inserallproduct(sqLiteDatabase,name, priceint, imageuri, desc);

                }

                adapter = new Recycleadapterclass(itemclasses, getActivity());
                recyclerView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Check Your Internt Conection", Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseOpener.deleteallptabel(sqLiteDatabase);
      Aysncallproducts aysncallproducts = new Aysncallproducts();
      aysncallproducts.execute();

    }



    private void initview(View view) {
        bottomNavigationView = view.findViewById(R.id.btnv);
        recyclerView = view.findViewById(R.id.recycle);
        filter = view.findViewById(R.id.txtfilter);
        spinner = view.findViewById(R.id.spinnerad);
        editsearch = view.findViewById(R.id.searchedit);

        spinnerarray.add("All");
        spinnerarray.add("Powder Pallete");
        spinnerarray.add("Eyeshow Pallete");
        spinnerarray.add("Foundation");
        spinnerarray.add("Brushes");
        spinnerarray.add("Makeup Box");
        spinnerarray.add("Lip Pallete");
        spinnerarray.add("Lipsticks");
        spinnerarray.add("Compact Powder");
        spinnerarray.add("Eyeslashes");
        spinnerarray.add("RingLight");
        spinnerarray.add("Spray");
        spinnerarray.add("Others");

    }


    public  class Aysncallproducts extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            databaseOpener = new DatabaseOpener(getContext());
            sqLiteDatabase = databaseOpener.getReadableDatabase();
            databaseOpener.createan(sqLiteDatabase);

        }

        @Override
        protected Void doInBackground(Void... voids) {


            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}
