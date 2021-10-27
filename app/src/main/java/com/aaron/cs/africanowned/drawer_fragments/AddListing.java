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
        nextbtn = view.findViewById(R.id.next);
        taglinetext = view.findViewById(R.id.tagline);
        taglinetext.setVisibility(View.GONE);
        taglineCheck = view.findViewById(R.id.taglineCheck);
//On check box select it display the view
        taglineCheck.setOnClickListener(view -> {
            if (taglineCheck.isChecked())
                taglinetext.setVisibility(view.getVisibility());

        });
        //Create dropdown list for countery
        dropdown=view.findViewById( R.id.textInputLayout2 );
        autocomplet=view.findViewById( R.id.countery );

        Locale[] listofCountery = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : listofCountery) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, countries);

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the your spinner
        autocomplet.setAdapter(countryAdapter);
        return v;
    }
}