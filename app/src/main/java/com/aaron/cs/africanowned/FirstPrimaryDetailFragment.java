package com.aaron.cs.africanowned;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.textfield.TextInputLayout;

public class FirstPrimaryDetailFragment extends Fragment {

    TextInputLayout taglinetext;
    Button nextbtn;
    View view;
    CheckBox taglineCheck;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first_primary_detail, container, false);
        nextbtn = view.findViewById(R.id.next);
        taglinetext = view.findViewById(R.id.tagline);
        taglinetext.setVisibility(View.GONE);
        taglineCheck = view.findViewById(R.id.taglineCheck);
//On check box select it display the view
        taglineCheck.setOnClickListener(view -> {
            if (taglineCheck.isChecked())
                taglinetext.setVisibility(view.getVisibility());

        });
       // nextbtn.setOnClickListener((OnClickListener) this);

        nextbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Fragment fragment = null;

             //   replaceFragment(fragment);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, new PrimareyDetailFragment());
                ft.commit();
            }






    });
        return view;


}}