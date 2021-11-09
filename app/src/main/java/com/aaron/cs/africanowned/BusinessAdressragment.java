package com.aaron.cs.africanowned;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class BusinessAdressragment extends Fragment {

    TextInputLayout address1,address2,lat,log;
    Button nextbtn2;
    View view;
//Button continue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_business_adressragment, container, false );

        nextbtn2 = view.findViewById( R.id.next2);
        address1 = view.findViewById(R.id.address);
        log = view.findViewById(R.id.longitude);
        lat = view.findViewById(R.id.latitude);
        address2= view.findViewById(R.id.custom);
        address1.setVisibility(View.GONE);
        address2.setVisibility(view.GONE);
        lat.setVisibility(view.GONE);
        log.setVisibility(view.GONE);

//Toogle buttons
        MaterialButtonToggleGroup toggle = view.findViewById(R.id.togleGroupButton);

        int buttonId = toggle.getCheckedButtonId();
        MaterialButton button = toggle.findViewById(buttonId);

        toggle.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                //Place code related to button1 here
                if (group.getCheckedButtonId() == R.id.searchByGoogle) {
                    address1.setVisibility( view.getVisibility() );
                    address2.setVisibility( View.GONE );
                    lat.setVisibility(view.GONE);
                    log.setVisibility(view.GONE);

                } else
                    if (group.getCheckedButtonId() == R.id.manualCoordinates) {
                        address1.setVisibility( View.GONE );
                        address2.setVisibility( view.getVisibility() );
                        lat.setVisibility( view.getVisibility() );
                        log.setVisibility( view.getVisibility() );
                    }
            }
        });

        // on click event next fragment will opne
        nextbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;


}}