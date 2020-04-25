package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Recycleadapterclass extends RecyclerView.Adapter<Recycleadapterclass.viewholder> {
    ArrayList<itemclass> itemclassArrayList = new ArrayList<>();
    int x=0;
    int change =0;
    Context context;

    public Recycleadapterclass(ArrayList<itemclass> itemclassArrayList, Context context) {
        this.itemclassArrayList = itemclassArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_adapter_layout, parent, false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        holder.recname.setText(itemclassArrayList.get(position).getName());
         x = itemclassArrayList.get(position).getPrice();
         change =x;
      //  Log.e(TAG, "onBindViewHolder: "+ x );
        holder.recprice.setText(String.valueOf(x));
        Picasso.with(context.getApplicationContext())
                .load(itemclassArrayList.get(position).getImageuri())
                .fit()
                .into(holder.recimage);
        Log.e(TAG, "onBindViewHolder: "+ itemclassArrayList.get(position).getImageuri());

         holder.parent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context.getApplicationContext(), SecondActivity.class);
                 intent.putExtra("name", itemclassArrayList.get(position).getName());
                 intent.putExtra("imageuri", itemclassArrayList.get(position).getImageuri());
                  intent.putExtra("price", itemclassArrayList.get(position).getPrice());
                  intent.putExtra("imageuri1", itemclassArrayList.get(position).getImageuri1());
                 //Log.e(TAG, "onClick: "+ itemclassArrayList.get(position).getPrice());
                 intent.putExtra("description", itemclassArrayList.get(position).getDescription());
                 context.startActivity(intent);
             }
         });

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(getItemCount()-1-position);
    }

    @Override
    public int getItemCount() {
        return itemclassArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView recimage;
        TextView recprice, recname;
        CardView parent;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            recimage = itemView.findViewById(R.id.recimage);
            recprice = itemView.findViewById(R.id.recycleprice);
            recname = itemView.findViewById(R.id.recyclename);
            parent = itemView.findViewById(R.id.cardparent);

        }
    }

    public void setItemclassArrayList(ArrayList<itemclass> itemclassArrayList) {
        this.itemclassArrayList = itemclassArrayList;
        notifyDataSetChanged();
    }
}
