package com.aaron.cs.africanowned;

import static com.aaron.cs.africanowned.R.string.open_hours;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.Resource;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

public class businessHouresFragment extends Fragment implements OnClickListener {

    TextInputLayout dropdown;
    AutoCompleteTextView autocomplet;
    String category;
    Button addBtn,nextbtn;
    View view;
    AppCompatSpinner getweekDays,getStartHour, getEndHours;
    //Spinner  getStartHour, getEndHours;
    CheckBox hours24;
    Button next3;
    String days;
    String start;
    String end;
    public String openhours=" 24 hours open";
    private LinearLayout parentLayout;


    List<String> teamList = new ArrayList<>();
    //  ArrayList<Cricketer> cricketersList = new ArrayList<>();



    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_business_houres, container, false );
        nextbtn = view.findViewById(R.id.next);
        dropdown = view.findViewById( R.id.textInputLayout2 );
        autocomplet = view.findViewById( R.id.category );
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( view.getContext(),
                R.array.spin_catagory, android.R.layout.simple_list_item_1 );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
// Apply the adapter to the autocomplet
        autocomplet.setAdapter( adapter );


        parentLayout = ( LinearLayout ) view.findViewById( R.id.parentViwLayout );
        getweekDays = view.findViewById( R.id.weekDays );
        getStartHour = view.findViewById( R.id.beginingHr );
        getEndHours = view.findViewById( R.id.endingHr );
        hours24 = view.findViewById( R.id.hours24 );
        addBtn = view.findViewById( R.id.addButton );

       addBtn.setOnClickListener(this);
       poplateWeekDays( view );
      poplateStartDate( view );
      populateEndDate(view );

      //next button
        nextbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, new SocilMediaFragment());
                ft.commit();
            }

        });
//        getSelectedItem();
        return view;

    }
    @Override
    public void onClick(View view) {
        addField();
    }
   public void addField() {
       View myview = getLayoutInflater().inflate( R.layout.business_hours,null );
               TextView weekday = (TextView)myview.findViewById( R.id.textWeekDays );
               TextView hours = (TextView) myview.findViewById( R.id.textBusinessHour );
               ImageView closeButton = myview.findViewById( R.id.imageClose );
        days = getweekDays.getSelectedItem().toString();
        start = getStartHour.getSelectedItem().toString();
        end = getEndHours.getSelectedItem().toString();
       if (days != null && start !=null && end !=null)
       {
           weekday.setText( days );
           hours.setText( start + " -" + end  + "\n");
           parentLayout.addView( myview );

       }
         else if(hours24.isChecked()) {

           hours.setText( openhours );

           parentLayout.addView( myview );
       }
         else
           Toast.makeText(getActivity(), "please select dates and hours", Toast.LENGTH_SHORT).show();

                closeButton.setOnClickListener( new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        parentLayout.removeView( myview );
                    }
                } );


     //   parentLayout.addView( myview );
    }


    public void onDelete(View v) {
        parentLayout.removeView( ( View ) v.getParent() );
    }

    public void poplateWeekDays(View view)
    {
        // Create an ArrayAdapter using the string array for Weekdays
        ArrayAdapter<CharSequence> adapterWeekdays = ArrayAdapter.createFromResource( view.getContext(),
            R.array.spin_weekDays, android.R.layout.simple_list_item_1 );
        adapterWeekdays.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // Apply the adapter to the spinner of weekdays
        getweekDays.setAdapter( adapterWeekdays );
       // getweekDays.setOnItemClickListener(new  a);
    }

    public void poplateStartDate(View view) {

        // Create an ArrayAdapter for start day
        ArrayAdapter<CharSequence> adapterStartHours = ArrayAdapter.createFromResource( view.getContext(),
        R.array.begningHours, android.R.layout.simple_list_item_1 );
        adapterStartHours.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // Apply the adapter to the spinner of weekdays
        getStartHour.setAdapter( adapterStartHours );
    }
    public void populateEndDate(View view) {
        // Create an ArrayAdapter for End hour
        ArrayAdapter<CharSequence> adapterEndingHours = ArrayAdapter.createFromResource( view.getContext(),
                R.array.endHours, android.R.layout.simple_list_item_1 );
        adapterEndingHours.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
       // Apply the adapter to the spinner of weekdays
        getEndHours.setAdapter( adapterEndingHours );
    }


}
