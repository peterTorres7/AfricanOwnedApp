package com.aaron.cs.africanowned;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class businessHouresFragment extends Fragment implements OnClickListener {

    TextInputLayout dropdown;
    AutoCompleteTextView autocomplet;
    String item;
    String three;
    Button addBtn,nextbtn;
    View view;
    AutoCompleteTextView getweekDays,getStartHour, getEndHours;
    //Spinner  getStartHour, getEndHours;
    CheckBox hours24;
    Button next3;
    String days;
    String start;
    String end;

    Boolean selected =true;
    String daysW;
    public String openhours=" 24 hours open";
    private LinearLayout parentLayout;
    ArrayAdapter adapterWeekdays;

    List<String> teamList = new ArrayList<String>();
    List<String> selectedDays = new ArrayList<String>();



    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_business_houres, container, false );

        initalizeViews(view);
        //  populateCategory(view);
        poplateWeekDays( view );
        poplateStartDate( view );
        populateEndDate(view );
        teamList.add("Select Days");
        teamList.add("Monday");teamList.add("Tuesday");teamList.add("Wednesday");
        teamList.add("Thursday");teamList.add("Friday");teamList.add("Saturday");teamList.add("Sunday");

        hours24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getStartHour.setEnabled(!isChecked);
                getEndHours.setEnabled(!isChecked);
            }
        });


        addBtn.setOnClickListener(this);


        nextbtn.setOnClickListener( new OnClickListener() {
            @Override

            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new SocilMediaFragment());
                ft.addToBackStack(three);
                ft.commit();

            }



        });

        return view;

    }


    private boolean validateCategory()
    {

        if ((item==null)){
            Toast.makeText(getActivity(), "please select  Category", Toast.LENGTH_SHORT).show();
            return false;


        }
        else return true;
    }

    private void initalizeViews(View view) {
        nextbtn = view.findViewById(R.id.next);
        dropdown = view.findViewById( R.id.textInputLayout2 );

        parentLayout = ( LinearLayout ) view.findViewById( R.id.parentViwLayout );
        getweekDays = view.findViewById( R.id.weekDays );
        getStartHour = view.findViewById( R.id.beginingHr );
        getEndHours = view.findViewById( R.id.endingHr );
        hours24 = view.findViewById( R.id.hours24 );
        addBtn = view.findViewById( R.id.addButton );
        nextbtn= view.findViewById( R.id.next );

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

        //getSelectedValu();

        if (days==null)
        {
            Toast.makeText(getActivity(), "please select  Weekdays", Toast.LENGTH_SHORT).show();
        }
        else if (start==null)
            Toast.makeText(getActivity(), "please select Starting hours", Toast.LENGTH_SHORT).show();

        else if(end==null)
            Toast.makeText(getActivity(), "please select Ending hours", Toast.LENGTH_SHORT).show();
        else {
            if (checkDayAlreadyPicked(days)) {
                Toast.makeText(getActivity(), "Day already picked: " + days, Toast.LENGTH_SHORT).show();
            } else {
                weekday.setText(days);
                hours.setText(start + " -" + end + "\n");
                selectedDays.add(days);
                parentLayout.addView(myview);
                resetViews();
            }
        }

        if(hours24.isChecked()) {
            if (checkDayAlreadyPicked(days)) {
                Toast.makeText(getActivity(), "Day already picked: " + days, Toast.LENGTH_SHORT).show();
            }
            else if (days==null) {
                Toast.makeText(getActivity(), "please select  Weekdays", Toast.LENGTH_SHORT).show();
            }
            else {
                weekday.setText(days);
                hours.setText(openhours);

                selectedDays.add(days);
                parentLayout.addView(myview);
                //getweekDays.setSelection(0);
            }
        }


        closeButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {

                parentLayout.removeView( myview );
                selectedDays.remove(days);
            }
        } );


    }

    private void resetViews() {

        getweekDays.setSelection(0);
        getStartHour.setSelection(0);
        getEndHours.setSelection(0);
        hours24.setChecked(false);
    }
    // Check if the Day is already Selected
    private boolean checkDayAlreadyPicked(String day) {
        for (int i = 0; i < selectedDays.size(); i++) {
            if (days.equals(selectedDays.get(i))) {
                return true;
            }
        }
        return false;
    }


    public void poplateWeekDays(View view)
    {


        adapterWeekdays = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, teamList);

        adapterWeekdays.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        getweekDays.setAdapter( adapterWeekdays );
        getweekDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                days = parent.getItemAtPosition(position).toString();
            }
        });

    }

    public void poplateStartDate(View view) {

        // Create an ArrayAdapter for start day
        ArrayAdapter<CharSequence> adapterStartHours = ArrayAdapter.createFromResource( view.getContext(),
                R.array.begningHours, android.R.layout.simple_list_item_1 );
        adapterStartHours.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // Apply the adapter to the spinner of weekdays
        getStartHour.setAdapter( adapterStartHours );
        getStartHour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                start = parent.getItemAtPosition(position).toString();
            }
        });
    }
    public void populateEndDate(View view) {
        // Create an ArrayAdapter for End hour
        ArrayAdapter<CharSequence> adapterEndingHours = ArrayAdapter.createFromResource( view.getContext(),
                R.array.endHours, android.R.layout.simple_list_item_1 );
        adapterEndingHours.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // Apply the adapter to the spinner of weekdays
        getEndHours.setAdapter( adapterEndingHours );
        getEndHours.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                end = parent.getItemAtPosition(position).toString();
            }
        });

    }



}

