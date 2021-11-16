package com.aaron.cs.africanowned;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class FirstPrimaryDetailFragment extends Fragment  {

    TextInputLayout taglinetext,dropdown,title;
    TextView errorMessage;
    MaterialButton nextbtn;
    CheckBox taglineCheck;
    Spinner autocomplet;
    boolean isAllFieldsChecked = false;
    String tLine,lTitle;

     Boolean isCountrySelected ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_primary_detail, container, false);

        nextbtn = view.findViewById(R.id.next);
        taglinetext = view.findViewById(R.id.tagline);
        errorMessage=view.findViewById(R.id.tvInvisibleError);

        title= view.findViewById(R.id.listingTitle);

        taglinetext.setVisibility(View.GONE);
        taglineCheck = view.findViewById(R.id.taglineCheck);
//On check box select it display the view
        taglineCheck.setOnClickListener(view1 -> {
            if (taglineCheck.isChecked())
                taglinetext.setVisibility(view1.getVisibility());


        });

        //Create dropdown list for countery
        dropdown=view.findViewById( R.id.textInputLayout2 );

        autocomplet=view.findViewById( R.id.countery );

        Locale[] listofCountery = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();
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

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>( getActivity(),
                android.R.layout.simple_spinner_item, countries );

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the your spinner
        autocomplet.setAdapter(countryAdapter);




        //Get Valu
     //   String tLine=taglinetext.getEditText().getText().toString().trim();
       // String lTitle=title.getEditText().getText().toString().trim();
    //    Boolean isCountrySelected =autocomplet.getSelectedItem().toString().trim().equals("Choose Countery");


      nextbtn.setOnClickListener( new View.OnClickListener() {


            @Override
            public void onClick(View view) {
          //      isAllFieldsChecked = ValidateAllFields();
                if(ValidateAllFields())
               {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new BusinessAdressragment());
                ft.addToBackStack(null);
                ft.commit();}
            }



        });
        return view;



    }

    private boolean ValidateAllFields() {


        String tLine=taglinetext.getEditText().getText().toString().trim();
      String lTitle=title.getEditText().getText().toString().trim();
      //  autocomplet.setOnItemSelectedListener(this);
        String counteryList = autocomplet.getSelectedItem().toString();
        if (counteryList.equals("Afghanistan")) {
            //errorMessage.setError("Countery required.");

            Toast.makeText(getActivity(), "please select  Countery", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (taglinetext.getVisibility() == View.VISIBLE && TextUtils.isEmpty(tLine))
        {
        taglinetext.setError("tagLine is  required.");
        return false;
    }
        if(TextUtils.isEmpty(lTitle)) {
            title.setError("List title is required.");
            return false;
        }


        return true;

    }


}