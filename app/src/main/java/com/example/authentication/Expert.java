package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;




public class Expert extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        Button buttonImplicit = (Button) findViewById(R.id.implicitint);
        buttonImplicit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent implicitIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://linktr.ee/therapybysurabhi"));
                startActivity(implicitIntent);
            }});

    }
}
