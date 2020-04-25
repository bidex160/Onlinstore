package com.example.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class pageradapter extends FragmentPagerAdapter {
    ArrayList<fragment> fragmentArrayList = new ArrayList<>();
    ArrayList<String> arrayList = new ArrayList<>();
    public pageradapter(@NonNull FragmentManager fm) {
        super(fm);
        arrayList.add("first");
        arrayList.add("second");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void setFragmentArrayList(ArrayList<fragment> fragmentArrayList) {
        this.fragmentArrayList = fragmentArrayList;
        notifyDataSetChanged();
    }
}
