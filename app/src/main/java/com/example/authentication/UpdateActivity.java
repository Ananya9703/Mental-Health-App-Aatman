package com.example.authentication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateActivity extends AppCompatActivity {


    TextView title_input;
    Button delete_button;


    String id, title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        title_input = findViewById(R.id.title_input2);
        delete_button = findViewById(R.id.delete_button);


        // First, call this method to get and set intent data
        getAndSetIntentData();


        // Set action bar title after getting the intent data
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }


        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                int intId = Integer.parseInt(id);
                myDB.deleteOneRow(intId);
                finish();
                Intent intent = new Intent(UpdateActivity.this, todo.class);
                startActivity(intent);
                finish();
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
    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")) {
            // Getting data from the intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");


            title_input.setText(title);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }


}




