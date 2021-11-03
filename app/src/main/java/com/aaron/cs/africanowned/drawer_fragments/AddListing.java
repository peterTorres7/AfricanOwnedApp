package com.aaron.cs.africanowned.drawer_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;

import com.aaron.cs.africanowned.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class AddListing extends Fragment {
    TextInputLayout taglinetext,dropdown;
    Button nextbtn;
    View view;
    CheckBox taglineCheck;

    AutoCompleteTextView autocomplet;
    String category;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_listing, container, false);
        return v;
    }
}