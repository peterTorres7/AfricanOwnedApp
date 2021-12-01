package com.aaron.cs.africanowned;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BusinessInformation extends AppCompatActivity {


    TextView companyName;
    TextView aboutUs;
    TextView category;
    TextView hours;
    TextView location;
    TextView website;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_information);

        companyName = findViewById(R.id.companyName);
        aboutUs = findViewById(R.id.about_us);
        hours = findViewById(R.id.hours);
        location = findViewById(R.id.location);
        website = findViewById(R.id.website_url);
        category = findViewById(R.id.category_name);

        companyName.setText(getIntent().getStringExtra("BusinessName"));
        aboutUs.setText(getIntent().getStringExtra("AboutUs"));
        category.setText(getIntent().getStringExtra("Category"));
        hours.setText(getIntent().getStringExtra("Hours"));
        location.setText(getIntent().getStringExtra("Location"));
        website.setText(getIntent().getStringExtra("website"));

    }
}