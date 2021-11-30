package com.aaron.cs.africanowned;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class FrequentlyAQFragment extends Fragment {
    LinearLayout parentlayout;
    Button add, skipBtn,nextButn;
    EditText faqR, faqAnswerR;
    View myView, view;
    String question,answer;
    private int count = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.faq_fragment, container, false);
        initViews();
        skipBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new MoreInformationFragment());
                ft.addToBackStack(null);
                ft.commit();
            }

        });
        nextButn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new MoreInformationFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(valideFaq() || count == 0) {
                   count++;
                   addFaq();
               }



            }


        });

        return view;
    }

    private void initViews() {
        parentlayout = view.findViewById(R.id.parent_layout);
        nextButn = view.findViewById(R.id.nextButton);
        skipBtn= view.findViewById(R.id.skip);
        myView = getLayoutInflater().inflate(R.layout.card_faq, null);
        faqR = myView.findViewById(R.id.faqRawQ);
        faqAnswerR = myView.findViewById(R.id.faqAnswerR);

        add = view.findViewById(R.id.addButton);
        skipBtn=view.findViewById(R.id.skip);
    }

    private void resetFildes() {
        faqR.setText("");
        faqAnswerR.setText("");
    }
    private void addFaq() {

        myView = getLayoutInflater().inflate(R.layout.card_faq, null);
        parentlayout.addView(myView);
     //  resetFildes();



      //  }
    }

    private boolean valideFaq()
    {
        faqR = myView.findViewById(R.id.faqRawQ);
        faqAnswerR = myView.findViewById(R.id.faqAnswerR);
        question=faqR.getText().toString();
        answer=faqAnswerR.getText().toString();

        if(TextUtils.isEmpty(question))
        {
            faqR.setError("List title is required.");
            return false;

        }
        if(TextUtils.isEmpty(answer))
        {
            faqAnswerR.setError("List title is required.");
            return false;

        }
    return true;

}}