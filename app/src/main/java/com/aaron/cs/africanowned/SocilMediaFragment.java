package com.aaron.cs.africanowned;

import static com.aaron.cs.africanowned.R.id.socialMediaAdress;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class SocilMediaFragment extends Fragment implements View.OnClickListener{
FragmentManager manager;
    Button addBtn, nextButn, skipBtn;
    View view;
    AutoCompleteTextView getSocialMedia;
    EditText getmediaAdress;
    String smedia;
    private LinearLayout parentLayout;
    List<String> selectedSocialMediaList = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_socil_media, container, false );

        iniitFildes(view);
        addBtn.setOnClickListener(this);
        poplateSocialMedia( view );


        nextButn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new FrequentlyAQFragment());
                ft.addToBackStack(null);
                ft.commit();
            }

        });

        return view;

    }

    private void iniitFildes(View view) {
        parentLayout = ( LinearLayout ) view.findViewById( R.id.parentViwLayout );
        getSocialMedia = view.findViewById( R.id.socialMedia );
        getmediaAdress = view.findViewById(R.id.socialMediaAdress );
        nextButn= view.findViewById(R.id.nextBUtn);
        addBtn = view.findViewById( R.id.addButton );
    }

    public void poplateSocialMedia(View view) {

        // Create an ArrayAdapter using the string array for socialmedia
        ArrayAdapter<CharSequence> addepterMedia = ArrayAdapter.createFromResource( view.getContext(),
                R.array.spin_socailMedia, android.R.layout.simple_list_item_1 );
        addepterMedia.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // Apply the adapter to the spinner of socialmedia
        getSocialMedia.setAdapter( addepterMedia );
        getSocialMedia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                smedia = parent.getItemAtPosition(position).toString();
            }
        });

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
        ImageView closeButton = myview.findViewById( R.id.imageClose );
        String name = smedia;
        String address = getmediaAdress.getText().toString();

             if (name==null){
                Toast.makeText(getActivity(), "please select media", Toast.LENGTH_SHORT).show();
             }
            else if (checkSocilaMediaAlreadyPicked(name))
                 Toast.makeText(getActivity(), "you already picked: " + name, Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(address))
             {
                 getmediaAdress.setError(" Adress Required");

             }
            else
            {
            nameMedia.setText( name );
            mediaAdress.setText( address  + "\n");
            parentLayout.addView( myview );
            selectedSocialMediaList.add(name);
             getSocialMedia.setSelection(0);
             getmediaAdress.setText("");
        }



       closeButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentLayout.removeView( myview );
                selectedSocialMediaList.remove(name);
            }
        } );

    }

    private boolean checkSocilaMediaAlreadyPicked(String mediaName) {
        for (int i = 0; i < selectedSocialMediaList.size(); i++) {
        if (mediaName.equals(selectedSocialMediaList.get(i))) {
            return true;
        }
    }
        return false;
    }

}