package com.aaron.cs.africanowned.drawer_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.aaron.cs.africanowned.Business;
import com.aaron.cs.africanowned.LogIn;
import com.aaron.cs.africanowned.R;
import com.aaron.cs.africanowned.RecyclerViewBusinessAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    DatabaseReference mBase;
    DatabaseReference yourRef;
    RecyclerViewBusinessAdapter adapter;
    List<String> businessNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Add the following lines to create RecyclerView
        recyclerView = v.findViewById(R.id.recyclerview);
        businessNames = new ArrayList<>();

        mBase = FirebaseDatabase.getInstance().getReference();
        yourRef = mBase.child("companies");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    String companyName = ds.getKey();
                    businessNames.add(companyName);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        yourRef.addListenerForSingleValueEvent(eventListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        adapter = new RecyclerViewBusinessAdapter(v.getContext(), businessNames);
        recyclerView.setAdapter(adapter);
        return v;

    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity().getApplicationContext(), LogIn.class));
    }
}