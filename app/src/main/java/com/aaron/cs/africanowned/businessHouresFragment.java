package com.aaron.cs.africanowned;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class businessHouresFragment extends Fragment implements OnClickListener, AdapterView.OnItemSelectedListener {

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
    String daysW;
    public String openhours=" 24 hours open";
    private LinearLayout parentLayout;
    ArrayAdapter adapterWeekdays;

    List<String> teamList = new ArrayList<String>();



    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_business_houres, container, false );
     // ArrayList<Cricketer> cricketersList = new ArrayList<>();
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
        nextbtn=view.findViewById( R.id.next );teamList.add("Select Days");
        teamList.add("Monday");teamList.add("Tuesday");teamList.add("Wednesday");
        teamList.add("Thursday");teamList.add("Friday");teamList.add("Saturday");teamList.add("Sunday");


        addBtn.setOnClickListener(this);
        poplateWeekDays( view );
        poplateStartDate( view );
        populateEndDate(view );


        nextbtn.setOnClickListener( new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new SocilMediaFragment());
                ft.addToBackStack(null);
                ft.commit();

            }



        });

        //next button

//        getSelectedItem();
        return view;

    }



    @Override
    public void onClick(View view)
    {


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
        if (days.equals("Select Days"))
            Toast.makeText(getActivity(), "please select  Weekdays", Toast.LENGTH_SHORT).show();

       else if (start.equals("Select Start Hr"))
            Toast.makeText(getActivity(), "please select Starting hours", Toast.LENGTH_SHORT).show();

        else if(end.equals("Select End Hr"))
            Toast.makeText(getActivity(), "please select Ending hours", Toast.LENGTH_SHORT).show();
        else {
            weekday.setText(days);
            hours.setText(start + " -" + end + "\n");
            parentLayout.addView(myview);
        }

        if(hours24.isChecked()) {

            hours.setText( openhours );

            parentLayout.addView( myview );
        }


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


        adapterWeekdays = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, teamList);

        adapterWeekdays.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        getweekDays.setAdapter( adapterWeekdays );

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


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(parent.getContext(),days)
        switch (view.getId())
        {
            case R.id.weekDays:
                days= parent.getItemAtPosition(position).toString();
                break;

            case R.id.beginingHr:
                start= parent.getItemAtPosition(position).toString();
                break;

            case R.id.endingHr:
                end= parent.getItemAtPosition(position).toString();
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //  Toast.makeText(parent.getContext(),"selectWeek", Toast.LENGTH_LONG).show();

    }
}

