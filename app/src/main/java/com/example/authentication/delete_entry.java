package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delete_entry extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private TextView Entry, Entry1, Date ;
    private Button  deleteCourseBtn;
    private JournalDBHandler dbHandler;
    String entrystr, entry1str, datestr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_entry);

        // initializing all our variables.
        Entry = findViewById(R.id.entry);
        Entry1 = findViewById(R.id.entry1);
        Date = findViewById(R.id.date);
        deleteCourseBtn = findViewById(R.id.deletebtn);

        // on below line we are initializing our dbhandler class.
        dbHandler = new JournalDBHandler(delete_entry.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        entrystr = getIntent().getStringExtra("entry");
        entry1str = getIntent().getStringExtra("entry1");
        datestr= getIntent().getStringExtra("date");

        // setting data to edit text
        // of our update activity.
        Entry.setText(entrystr);
        Entry1.setText(entry1str);
        Date.setText(datestr);



        // adding on click listener for delete button to delete our course.
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteEntry(datestr);
                Toast.makeText(delete_entry.this, "Deleted the entry", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(delete_entry.this, ViewEntry.class);
                startActivity(i);
            }
        });
    }

}
