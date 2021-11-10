package com.aaron.cs.africanowned.drawer_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.aaron.cs.africanowned.LogIn;
import com.aaron.cs.africanowned.R;
import com.aaron.cs.africanowned.RecyclerViewBusinessAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    List<String> arrList = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Add the following lines to create RecyclerView
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        arrList.add("Business 1");
        arrList.add("Business 2");
        arrList.add("Business 3");
        arrList.add("Business 4");
        arrList.add("Business 5");
        arrList.add("Business 6");
        arrList.add("Business 7");
        arrList.add("Business 8");
        arrList.add("Business 9");
        arrList.add("Business 10");
        arrList.add("Business 11");
        arrList.add("Business 12");
        arrList.add("Business 13");
        arrList.add("Business 14");
        arrList.add("Business 15");
        arrList.add("Business 16");
        arrList.add("Business 17");
        arrList.add("Business 18");
        arrList.add("Business 19");
        arrList.add("Business 20");

        recyclerView.setAdapter(new RecyclerViewBusinessAdapter(getActivity(), arrList));

        return v;
    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity().getApplicationContext(), LogIn.class));
    }
}