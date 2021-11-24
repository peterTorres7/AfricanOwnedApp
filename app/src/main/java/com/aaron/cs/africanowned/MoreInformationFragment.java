package com.aaron.cs.africanowned;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MoreInformationFragment extends Fragment {
Button nextbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate( R.layout.fragment_more_information, container, false );
        nextbtn = view.findViewById(R.id.next);
        nextbtn.setOnClickListener( new View.OnClickListener() {
            @Override

            public void onClick(View view) {

               FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new Media());
                ft.addToBackStack(null);
                ft.commit();

            }



        });


        return view;
    }


}

