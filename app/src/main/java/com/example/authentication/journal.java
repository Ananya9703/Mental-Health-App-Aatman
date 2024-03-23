package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class journal extends AppCompatActivity {
    EditText e1,e2;
    Button savebtn, dispbtn;
    JournalDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);


        e1 = findViewById(R.id.entry1);
        e2 = findViewById(R.id.entry2);

        savebtn = findViewById(R.id.jsave);
        dispbtn=findViewById(R.id.jdisp);
        dbHandler = new JournalDBHandler(this);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userentry1 = e1.getText().toString();
                String userentry2 = e2.getText().toString();


                if (userentry1.isEmpty() && userentry2.isEmpty()) {
                    Toast.makeText(journal.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addEntry( userentry1,userentry2);

                // after adding the data we are displaying a toast message.
                Toast.makeText(journal.this, "Entries have been added.", Toast.LENGTH_SHORT).show();
                e1.setText("");
                e2.setText("");


            }

        });
        dispbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(journal.this, ViewEntry.class);
                startActivity(i);
            }
        });
    }
    }
