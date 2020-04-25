package com.example.store;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class fragment extends Fragment {
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlayout, container, false);
        imageView = view.findViewById(R.id.imgpager);
        imageView.setImageResource(R.drawable.ic_home);
        Bundle bundle = getArguments();
        if (bundle != null){
            String urls = bundle.getString("imageuri", "");
            Log.e(TAG, "onCreateView: " + urls );

           Glide.with(getActivity())
                    .asBitmap()
                    .load(urls)
                    .into(imageView);
        }
        return view;
    }
}
