package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.widget.Button;
import android.widget.ImageButton;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class landingpg extends AppCompatActivity {


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpg);


        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);


        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        ImageButton superprettybtn = findViewById(R.id.superpretty);
        ImageButton lilhappybtn = findViewById(R.id.lilhappy);
        ImageButton smugbtn = findViewById(R.id.smug);
        ImageButton indifferentbtn = findViewById(R.id.indifferent);
        ImageButton sadbtn = findViewById(R.id.sad);
        TextView tv = findViewById(R.id.customtext);


        superprettybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customstring = getString(R.string.superprettystring);
                tv.setText(customstring);
            }
        });


        lilhappybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customstring = getString(R.string.lilhappystring);
                tv.setText(customstring);
            }
        });


        indifferentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customstring = getString(R.string.indifferentstring);
                tv.setText(customstring);
            }
        });


        superprettybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customstring = getString(R.string.superprettystring);
                tv.setText(customstring);
            }
        });


        smugbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customstring = getString(R.string.smugstring);
                tv.setText(customstring);
            }
        });


        sadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customstring = getString(R.string.sadstring);
                tv.setText(customstring);
            }
        });


        // to make the Navigation drawer icon always appear on the action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id= menuItem.getItemId();

                //handle menu items
                if (id == R.id.nav_meditation) {
                    Intent intent5 = new Intent(landingpg.this, com.example.authentication.Meditation.class);
                    startActivity(intent5);
                } else if (id == R.id.nav_breathwork) {
                    Intent intent = new Intent(landingpg.this, com.example.authentication.Breath.class);
                    startActivity(intent);
                } else if (id == R.id.nav_quiz) {
                    Intent intent2 = new Intent(landingpg.this, com.example.authentication.Quiz.class);
                    startActivity(intent2);
                } else if (id == R.id.nav_blog) {
                    Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://dm602622.wixsite.com/aatman/news"));
                    startActivity(intent1);
                } else if (id == R.id.nav_journal) {
                    Intent intent3 = new Intent(landingpg.this, com.example.authentication.journal.class);
                    startActivity(intent3);
                }else if (id == R.id.nav_todo) {
                    Intent intent4 = new Intent(landingpg.this, com.example.authentication.todo.class);
                    startActivity(intent4);
                }else if (id == R.id.nav_expert) {
                    Intent intent6 = new Intent(landingpg.this, com.example.authentication.Expert.class);
                    startActivity(intent6);
                }
                else if (id==R.id.nav_logout){
                    Intent intent7 = new Intent(landingpg.this, com.example.authentication.signin.class);
                    startActivity(intent7);
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Navigate to the main page (assuming landingpg is your main page)
        super.onBackPressed();
        Intent intent = new Intent(this, landingpg.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Finish the current activity to release resources
        finish();
    }
    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
