package com.example.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AddData extends AppCompatActivity {
  TextView ptxt, ntxt, dtxt;
  ImageView img, img2;
  EditText nedit, dedit, pedit;
  Button button;
  DatabaseReference databaseReference;
   StorageReference storageReference;
    Spinner spinner;
    List<String> array;
ArrayList<String> taskresult;

     Switch aSwitch;
     BottomNavigationView bottomNavigationView;
 private StorageTask storageTask;
 final private static int re =1, re2=2;
 ArrayList<Uri> arruri;
 Uri imageuri, imageuri2;
 int up=0, k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        taskresult = new ArrayList<>();
        arruri = new ArrayList<>();
        array = new ArrayList<>();

        initviews();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b == true){
                  databaseReference = FirebaseDatabase.getInstance().getReference("sicostore");
                   storageReference = FirebaseStorage.getInstance().getReference("sicostore");
                   uploadfile();
               }
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
                        Intent intenth = new Intent(AddData.this, MainActivity.class);
                        startActivity(intenth);
                        break;
                    case R.id.Cartf:


                        break;
                    default:
                        break;
                }
                return false;
            }
        });


       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               switch (i){
                   case 1:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Powder Pallete");
                       storageReference = FirebaseStorage.getInstance().getReference("Powder Pallete");


                       break;
                   case 2:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Eyeshadow Pallete");
                       storageReference = FirebaseStorage.getInstance().getReference("Eyeshadow Pallete");

                       break;
                   case 3:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Foundation");
                       storageReference = FirebaseStorage.getInstance().getReference("Foundation");



                       break;
                   case 4:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Brushes");
                       storageReference = FirebaseStorage.getInstance().getReference("Brushes");

                       break;
                   case 5:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Make Up BOX");
                       storageReference = FirebaseStorage.getInstance().getReference("Make Up BOX");
                       break;
                   case 6:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Lip Pallete");
                       storageReference = FirebaseStorage.getInstance().getReference("Lip Pallete");

                       break;
                   case 7:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Lipsticks");
                       storageReference = FirebaseStorage.getInstance().getReference("Lipsticks");

                       break;
                   case 8:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Compact");
                       storageReference = FirebaseStorage.getInstance().getReference("Compact");

                       break;
                   case 9:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Eyelashes");
                       storageReference = FirebaseStorage.getInstance().getReference("Eyelashes");

                       break;
                   case 10:
                       databaseReference = FirebaseDatabase.getInstance().getReference("RingLight");
                       storageReference = FirebaseStorage.getInstance().getReference("RingLight");

                       break;
                   case 11:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Spray");
                       storageReference = FirebaseStorage.getInstance().getReference("Spray");

                       break;
                   case 12:
                       databaseReference = FirebaseDatabase.getInstance().getReference("Others");
                       storageReference = FirebaseStorage.getInstance().getReference("Others");

                       break;
                    default:

                        break;
               }

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openfilechoo(re);
                Picasso.with(AddData.this).load(imageuri).into(img);
                Log.e(TAG, "onClick: "+imageuri );
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aSwitch.setChecked(false);
                if(up != 0 && k!=0){
                    up=0;
                    k=0;
                }

                uploadfile();
                Log.e(TAG, "onClick: " );
            }
        });



    }
    private String getextension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadfile() {
        Log.e(TAG, "uploadfile: "+ arruri.size() );
        if (arruri != null){
       while (up < arruri.size()) {
      final StorageReference fileref = storageReference.child(System.currentTimeMillis()+"."+getextension(arruri.get(k)));
        fileref.putFile(arruri.get(k))
            .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
        @Override
        public void onComplete(@NonNull Task<Uri> task) {

            if (task.isSuccessful()) {

                taskresult.add(task.getResult().toString());
                Log.e(TAG, "onComplete:" + taskresult);
               if (taskresult.size() == 2){

                    Log.e(TAG, "onComplete:"+ taskresult.size());
                    int p = Integer.parseInt(pedit.getText().toString());
                      itemclass itemclass = new itemclass(taskresult.get(0), task.getResult().toString(), nedit.getText().toString().trim(),
                     p, dedit.getText().toString().trim());
                   String id =    databaseReference.push().getKey();

                     databaseReference.child(id).setValue(itemclass);
                     taskresult.clear();

                }







                Toast.makeText(AddData.this, "Succesful", Toast.LENGTH_LONG).show();
                //  Intent intent = new Intent(AddData.this, MainActivity.class);
                //  startActivity(intent);

            } else {
                Log.e(TAG, "onComplete:err " );

            }

        }
    });
      Log.e(TAG, "uploadfile: "+ up );
    up++;
    k++;
}

        }else {
            Log.e(TAG, "uploadfile: N)))))" );

        }

    }

    private void openfilechoo(int r) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, r);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: " + requestCode);

        if (requestCode == re && resultCode == RESULT_OK &&   data!=null && data.getClipData()!= null){
            int currentItem = 0;
            while (currentItem < 2) {
              imageuri2 = data.getClipData().getItemAt(currentItem).getUri();


                Log.e(TAG, "onActivityResult: "+imageuri2 );
                Picasso.with(this).load(data.getClipData().getItemAt(0).getUri()).into(img);
                Picasso.with(this).load(data.getClipData().getItemAt(1).getUri()).into(img2);

              arruri.add(imageuri2);
                currentItem = currentItem + 1;
            }



        }


        }




    private void initviews() {
        ntxt = findViewById(R.id.nametxt);
        ptxt = findViewById(R.id.pricetxt);
        dtxt = findViewById(R.id.desctxt);
        img = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView2);
        button = findViewById(R.id.addbt);
        nedit = findViewById(R.id.nameedit);
        pedit = findViewById(R.id.priceedit);
        dedit = findViewById(R.id.Descedit);
        spinner = findViewById(R.id.sp);
        aSwitch = findViewById(R.id.swi);
        bottomNavigationView = findViewById(R.id.cardmenu);
         array.add("All");
        array.add("Powder Pallete");
        array.add("Eyeshow Pallete");
        array.add("Foundation");
        array.add("Brushes");
        array.add("Makeup Box");
        array.add("Lip Pallete");
        array.add("Lipsticks");
        array.add("Compact Powder");
        array.add("Eyeslashes");
        array.add("RingLight");
        array.add("Spray");
        array.add("Others");




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, array
        );
        spinner.setAdapter(adapter);

    }

}
