package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Meditation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        Button buttonExplicit = (Button) findViewById(R.id.explicitint);
        Button buttonExplicit1 = (Button) findViewById(R.id.explicitint1);
        Button buttonExplicit2 = (Button) findViewById(R.id.explicitint2);
        buttonExplicit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent explicitIntent = new Intent(Meditation.this, sleep.class);
                startActivity(explicitIntent);
            }});
        buttonExplicit1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent explicitIntent = new Intent(Meditation.this, focus.class);
                startActivity(explicitIntent);
            }});
        buttonExplicit2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent explicitIntent = new Intent(Meditation.this, relax.class);
                startActivity(explicitIntent);
            }});






    }
}
