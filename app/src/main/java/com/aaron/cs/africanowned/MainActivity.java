package com.aaron.cs.africanowned;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button callFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragment call
        callFragment=findViewById(R.id.add_listing);
        callFragment.setOnClickListener(this);
      //  getSupportFragmentManager().beginTransaction().add(R.id.container,new PrimareyDetailFragment()).commit();
    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LogIn.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.add_listing){


            getSupportFragmentManager().beginTransaction().replace(R.id.container,new FirstPrimaryDetailFragment()).commit();


        }
    }
}