package com.example.store;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class DialogFrag extends DialogFragment {
    private List<String> mselectre;
    ListView listView,li2 ;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            mselectre = new ArrayList<>();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.colorcheckdialog,null);
        listView = view.findViewById(R.id.colorcheck);
        //li2 = view.findViewById(R.id.l);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] items = {"weee", "rtyuuu"};
        String[] item = {"we45", "777"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_multichoice, items);
      //  ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        listView.setAdapter(adapter);
    //    li2.setAdapter(adapter1);
                builder.setView(view);
        builder.setTitle("Colors");



        return builder.create();
    }
}
