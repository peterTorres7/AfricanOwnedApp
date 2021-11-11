package com.aaron.cs.africanowned;

import static com.aaron.cs.africanowned.R.id.socialMediaAdress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class SocilMediaFragment extends Fragment implements View.OnClickListener {


    Button addBtn, nextButn;
    View view;
    AppCompatSpinner getSocialMedia;
    EditText getmediaAdress;
    String smedia;
    private LinearLayout parentLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_socil_media, container, false );

        parentLayout = ( LinearLayout ) view.findViewById( R.id.parentViwLayout );
        getSocialMedia = view.findViewById( R.id.socialMedia );
        getmediaAdress = view.findViewById(R.id.socialMediaAdress );
        addBtn = view.findViewById( R.id.addButton );
        addBtn.setOnClickListener(this);
        poplateSocialMedia( view );
        nextButn=view.findViewById(R.id.nextBUtn);
       nextButn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new FrequentlyAQFragment());
                ft.commit();
            }

        });
        return view;

    }

    public void poplateSocialMedia(View view) {

        // Create an ArrayAdapter using the string array for Weekdays
        ArrayAdapter<CharSequence> addepterMedia = ArrayAdapter.createFromResource( view.getContext(),
                R.array.spin_socailMedia, android.R.layout.simple_list_item_1 );
        addepterMedia.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // Apply the adapter to the spinner of weekdays
        getSocialMedia.setAdapter( addepterMedia );

        // getweekDays.setOnItemClickListener(new  a);
    }

    @Override
    public void onClick(View view)
    {
        addField();
    }
    private void addField() {
        View myview = getLayoutInflater().inflate( R.layout.soial_m_raw,null );
        TextView nameMedia = myview.findViewById( R.id.media );
        TextView mediaAdress = (TextView) myview.findViewById(R.id.mediaWeb );
        String name = getSocialMedia.getSelectedItem().toString();
        String address = getmediaAdress.getText().toString();
        if (name != null && address!=null)
        {
            nameMedia.setText( name );
            mediaAdress.setText( address  + "\n");
            parentLayout.addView( myview );
        }
        else
            Toast.makeText(getActivity(), "please select media", Toast.LENGTH_SHORT).show();

       /* closeButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentLayout.removeView( myview );
            }
        } );*/

    }
}