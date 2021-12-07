package com.aaron.cs.africanowned;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aaron.cs.africanowned.drawer_fragments.AboutUs;
import com.google.android.material.textfield.TextInputLayout;


public class MoreInformationFragment extends Fragment {
    Button nextbtn;
    TextInputLayout desc, tags;
    String getKeywords, getDescriptiom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_information, container, false);
        nextbtn = view.findViewById(R.id.next);
        desc = view.findViewById(R.id.listingTitle);
        tags = view.findViewById(R.id.key_tags);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getKeywords = tags.getEditText().getText().toString().trim();
                getDescriptiom = desc.getEditText().getText().toString().trim();
                // if (ValidateAllFields()) {
                if (TextUtils.isEmpty(getDescriptiom)) {
                    desc.setError("Description is required.");
                   return ;
                }
               if (TextUtils.isEmpty(getKeywords)) {
                    tags.setError("Tags  is required.");
                    return;
                }
             /*   FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new FirstPrimaryDetailFragment());
                ft.addToBackStack(null);
                ft.commit();*/
                //      }
            }
        });
        return view;


    }


}



