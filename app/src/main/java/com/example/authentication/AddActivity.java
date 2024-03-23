package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Calendar;


public class AddActivity extends AppCompatActivity {


    EditText title_input;
    TextView chosendate;
    Button add_button, date_button;
    public String selecteddate = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        title_input = findViewById(R.id.title_input);
        add_button = findViewById(R.id.add_button);
        date_button = findViewById(R.id.buttonPickDate);


        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showDatePickerDialog();
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(title_input.getText().toString().trim(), selecteddate);
                Intent intent = new Intent(AddActivity.this, todo.class);
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
    private String showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        chosendate = findViewById(R.id.chosendate);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        selecteddate = year + "-" + (month + 1) + "-" + day;
                        String formatdate = day + "/" + (month+1) + "/" + year;
                        chosendate.setText(formatdate);
                    }
                }, year, month, day);
        datePickerDialog.show();
        return selecteddate;
    }
}


