package com.aaron.cs.africanowned;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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

import java.util.Arrays;

public class BusinessAdressragment extends Fragment {

    TextInputLayout address1,address2,lat,log,cityname,phoneno,webaddress;
    Button nextbtn2;
String two;

    View view;
    MaterialButtonToggleGroup toggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_business_adressragment, container, false );

        initalizeVies(view);

        address1.setVisibility(View.GONE);

        for (TextInputLayout textInputLayout : Arrays.asList( address2, lat, log )) {
            textInputLayout.setVisibility( View.GONE );
        }

//Toogle buttons
        toggle = view.findViewById(R.id.togleGroupButton);

        int buttonId = toggle.getCheckedButtonId();
        MaterialButton button = toggle.findViewById(buttonId);

        toggle.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                //Place code related to button1 here
                if (group.getCheckedButtonId() == R.id.searchByGoogle) {
                    address1.setVisibility( view.getVisibility() );
                    address2.setVisibility( View.GONE );
                    lat.setVisibility( View.GONE );
                    log.setVisibility( View.GONE );

                } else
                    if (group.getCheckedButtonId() == R.id.manualCoordinates) {
                        address1.setVisibility( View.GONE );
                        address2.setVisibility( view.getVisibility() );
                        lat.setVisibility( view.getVisibility() );
                        log.setVisibility( view.getVisibility() );
                    }
            }
        });
// on click event next fragment will open
        nextbtn2.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //if it is vallid it will open business Hours Fragment
            if (ValidateAllFields()) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new businessHouresFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        }
        });
        return view;



    }

    private void initalizeVies(View view) {
        nextbtn2 = view.findViewById( R.id.next2);
        address1 = view.findViewById(R.id.address);
        log = view.findViewById(R.id.longitude);
        lat = view.findViewById(R.id.latitude);
        address2= view.findViewById(R.id.custom);
        cityname= view.findViewById(R.id.city);
        phoneno= view.findViewById(R.id.phone);
        webaddress= view.findViewById(R.id.website);
    }
    //check if the Fields are empity and display message

    private boolean ValidateAllFields()
    {
        String get_lon = log.getEditText().getText().toString().trim();
        String get_lat = lat.getEditText().getText().toString().trim();
        String get_customAdress = address2.getEditText().getText().toString().trim();
        String get_city = cityname.getEditText().getText().toString().trim();
        String get_phoneNo = phoneno.getEditText().getText().toString().trim();
        String get_web = webaddress.getEditText().getText().toString().trim();
        String get_address1 = address1.getEditText().getText().toString().trim();

        if (log.getVisibility() == View.VISIBLE && TextUtils.isEmpty(get_lon)) {
            log.setError("Longtude Required");
            return false;
        }

        if (lat.getVisibility() == View.VISIBLE && TextUtils.isEmpty(get_lat)) {
            lat.setError("Latitude Required");
            return false;
        }
        if (address2.getVisibility() == View.VISIBLE && TextUtils.isEmpty(get_customAdress)) {
            address2.setError("Address Required");
            return false;
        }
        if (TextUtils.isEmpty(get_city)) {
            cityname.setError("City Required");
            return false;
        }
        if (TextUtils.isEmpty(get_phoneNo)) {
            phoneno.setError("Phone Number Required");
            return false;
        }


        if (TextUtils.isEmpty(get_web)) {
            webaddress.setError("Web Address Required");
            return false;
        }
        if (address1.getVisibility() == View.VISIBLE && TextUtils.isEmpty(get_address1)) {
            address1.setError("Address Required");
            return false;
        }
        if (!android.util.Patterns.PHONE.matcher(get_phoneNo).matches())

            {
        phoneno.setError("This is not valid phone No");
        return false;
    }
        if (!Patterns.WEB_URL.matcher(get_web).matches())

        {
            webaddress.setError("This is not valid Website");
            return false;
        }



        return true;

    }
}