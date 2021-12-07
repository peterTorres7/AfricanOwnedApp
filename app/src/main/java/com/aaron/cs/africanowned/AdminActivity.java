package com.aaron.cs.africanowned;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    DatabaseReference mBase;
    DatabaseReference yourRef;
    RecyclerViewBusinessAdminAdapter adapter;

    public static Button update;

    List<String> businessNames;
    List<Boolean> verified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Add the following lines to create RecyclerView
        recyclerView = findViewById(R.id.recyclerviewAdmin);
        businessNames = new ArrayList<>();
        verified = new ArrayList<>();


        mBase = FirebaseDatabase.getInstance().getReference();
        yourRef = mBase.child("companies");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {

                    String companyName = ds.getKey();
                    businessNames.add(companyName);

                    for(DataSnapshot dSnapshot : ds.getChildren())
                    {
                        Log.d("LOGGGING", dSnapshot.getKey());
                        if(dSnapshot.getKey().equalsIgnoreCase("verified"))
                        {
                            verified.add(Boolean.parseBoolean(dSnapshot.getValue().toString()));
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        yourRef.addListenerForSingleValueEvent(eventListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminActivity.this));

        adapter = new RecyclerViewBusinessAdminAdapter(AdminActivity.this, businessNames, verified);
        recyclerView.setAdapter(adapter);



    }

    public void logOut(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(AdminActivity.this, LogIn.class));
    }
}