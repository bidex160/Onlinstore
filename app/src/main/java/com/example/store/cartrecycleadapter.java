package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class cartrecycleadapter extends RecyclerView.Adapter<cartrecycleadapter.viewholder>{
    interface Passdelete{

      void passit(dbitems dbitems);
      void edittextchange(int oldqt, int nwqt, int oldprice);
    }

    private Passdelete passdelete;
ArrayList<dbitems> dbitemsArrayList = new ArrayList<>();
Context context;


    public cartrecycleadapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartadapterlayout, parent, false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {
        holder.setproductname.setText(dbitemsArrayList.get(position).getName());
       holder.setsubtotal.setText(String.valueOf(dbitemsArrayList.get(position).getTotal()));
       holder.setprice.setText(String.valueOf(dbitemsArrayList.get(position).getPrice()));

       holder.editTextqtt.setText(String.valueOf(dbitemsArrayList.get(position).getQt()));

        Glide.with(context)
                .asBitmap()
                .load(dbitemsArrayList.get(position).getImageuri())
                .into(holder.imageViewcart);

        holder.cancelimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 try {

                     passdelete = (Passdelete)context;
                     passdelete.passit(dbitemsArrayList.get(position));
                    // dbitemsArrayList.remove(position);


                 }catch (Exception e){
                     Log.e(TAG, "onClick: " + e );



             }



            }
        });
     holder.editTextqtt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             holder.editqt.setVisibility(View.VISIBLE);
         }
     });
        holder.editqt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 !=0){
                    try {
                        passdelete = (Passdelete)context;


                            String x = holder.editqt.getText().toString();



                       passdelete.edittextchange(dbitemsArrayList.get(position).getQt(),Integer.parseInt(x), dbitemsArrayList.get(position).getTotal());
                       // Log.e(TAG, "onTextChanged: "+ oldtxt );
                    }catch (NumberFormatException ex){
                        Log.e(TAG, "onClick: " + ex );

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e(TAG, "afterTextChanged: " );
                holder.editqt.setVisibility(View.INVISIBLE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dbitemsArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView productnametxt, setproductname, pricetxt, setprice, subtotaltxt, setsubtotal, qtttxt, editqt;
        ImageView imageViewcart, cancelimg;
        EditText editTextqtt;
        CardView parent;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            productnametxt = itemView.findViewById(R.id.producttname);
            imageViewcart = itemView.findViewById(R.id.proceedimg);
            pricetxt = itemView.findViewById(R.id.pricetxtcartview);
            qtttxt = itemView.findViewById(R.id.setqqtcartview);
            subtotaltxt = itemView.findViewById(R.id.subtotalttxtcartview);

            setproductname = itemView.findViewById(R.id.setproductname);
            setsubtotal = itemView.findViewById(R.id.setsubtotalcartview);
            setprice = itemView.findViewById(R.id.setpricecartview);




            editTextqtt = itemView.findViewById(R.id.setqqtcartview);

            cancelimg = itemView.findViewById(R.id.cancelimg);
            editqt = itemView.findViewById(R.id.editqqtcartview);
            parent = itemView.findViewById(R.id.cardparent);

            DatabaseOpener databaseOpener = new DatabaseOpener(context.getApplicationContext());


        }
    }

    public void setDbitemsArrayList(ArrayList<dbitems> dbitemsArrayList) {
        this.dbitemsArrayList = dbitemsArrayList;
        notifyDataSetChanged();
    }
}
