package com.aaron.cs.africanowned;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.aaron.cs.africanowned.drawer_fragments.AboutUs;
import com.aaron.cs.africanowned.drawer_fragments.AddListing;
import com.aaron.cs.africanowned.drawer_fragments.HomeFragment;
import com.aaron.cs.africanowned.drawer_fragments.ProfileFragment;
import com.aaron.cs.africanowned.drawer_fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar t;




    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        t = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, t, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        displayView(R.id.nav_home);
        t.setTitle("African Owned");

        navigationView.setNavigationItemSelectedListener(item -> {
            displayView(item.getItemId());
            return true;
        });

    }

    public void displayView(int viewId)
    {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch(viewId)
        {
            case R.id.nav_home:
                fragment = new HomeFragment();
                title = "African Owned";
                viewIsAtHome = true;
                break;
            case R.id.nav_listing:
                fragment = new AddListing();
                title = "Add Listing";
                viewIsAtHome = false;
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                title = "Profile";
                viewIsAtHome = false;
                break;
            case R.id.nav_about_us:
                fragment = new AboutUs();
                title = "About Us";
                viewIsAtHome = false;
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                title = "Settings";
                viewIsAtHome = false;
                break;
        }

        if(fragment != null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, fragment);
            ft.commit();
        }

        if(t != null)
        {
            t.setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LogIn.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) {
            displayView(R.id.nav_home);
        } else {
            moveTaskToBack(true);
        }
    }

}