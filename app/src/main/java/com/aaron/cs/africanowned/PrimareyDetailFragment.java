package com.aaron.cs.africanowned;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;


public class PrimareyDetailFragment extends Fragment {



String countries[];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_primarey_detail, container, false);
    /*   AutoCompleteTextView textView= (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextViewcCountry);
        countries= getActivity().getResources().getStringArray(R.array.spinner_country_choices);
        ArrayAdapter<String> adapters =
                new ArrayAdapter<String>(this,R.layout.dropdawn_choices);*/


        return view;

    }

    private void ArrayAdapter(PrimareyDetailFragment requireContext, int dropdawn_choices, String[] myCountry) {
    }
}