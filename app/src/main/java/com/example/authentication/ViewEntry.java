package com.example.authentication;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewEntry extends AppCompatActivity {

    // creating variables for our array list, 
    // dbhandler, adapter and recycler view.
    private ArrayList<JournalModal> JournalModalArrayList;
    private JournalDBHandler dbHandler;
    private JournalRVAdapter JournalRVAdapter;
    private RecyclerView JournalRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);



        // initializing our all variables.
        JournalModalArrayList = new ArrayList<>();
        dbHandler = new JournalDBHandler(ViewEntry.this);

        // getting our Journal array 
        // list from db handler class.
        JournalModalArrayList = dbHandler.getUserEntry();

        // on below line passing our array list to our adapter class.
        JournalRVAdapter = new JournalRVAdapter(JournalModalArrayList, ViewEntry.this);
        JournalRV = findViewById(R.id.idRVEntries);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewEntry.this, RecyclerView.VERTICAL, false);
        JournalRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        JournalRV.setAdapter(JournalRVAdapter);


    }
    @Override
    public void onBackPressed() {
        // Navigate to the main page (assuming landingpg is your main page)
        super.onBackPressed();
        Intent intent = new Intent(ViewEntry.this, journal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Finish the current activity to release resources
        finish();
    }
}
