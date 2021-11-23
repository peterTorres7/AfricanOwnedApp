package com.aaron.cs.africanowned;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class FrequentlyAQFragment extends Fragment {
    LinearLayout parentlayout;
    Button add,nextButn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate( R.layout.faq_fragment, container, false );

        parentlayout = view.findViewById( R.id.parent_layout );
        nextButn=view.findViewById( R.id.nextButton );

        add = view.findViewById( R.id.addButton );nextButn.setOnClickListener( new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new MoreInformationFragment());
                ft.commit();
            }
        } );

        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFaq();
            }




        });

        return view;
    }

    private void addFaq() {

        View myView = getLayoutInflater().inflate( R.layout.card_faq, null );
        parentlayout.addView( myView );


    }
}

