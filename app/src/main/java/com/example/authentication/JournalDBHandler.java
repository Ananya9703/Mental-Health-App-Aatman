package com.example.authentication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JournalDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "JournalDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ENTRIES = "entries";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ENTRY = "entry";
    private static final String COLUMN_ENTRY1 = "entry1";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ENTRY_USER_EMAIL = "user_mail"; // Add this line

    public JournalDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_ENTRIES +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENTRY_USER_EMAIL + " TEXT, " +
                COLUMN_ENTRY1 + " TEXT, " +
                COLUMN_ENTRY + " TEXT, " +
                COLUMN_DATE + " TEXT);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public long addEntry(String entryText, String entryText1) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Get the current date and time
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        ContentValues values = new ContentValues();
        values.put(COLUMN_ENTRY, entryText);
        values.put(COLUMN_ENTRY1, entryText1);
        values.put(COLUMN_DATE, currentDate);
        // Insert the data into the "entries" table
        long result = db.insert(TABLE_ENTRIES, null, values);

        // Close the database connection
        db.close();

        return result;
    }

    public ArrayList<JournalModal> getUserEntry() {
        ArrayList<JournalModal> entriesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Select all entries from the "entries" table
        Cursor cursor
                = db.rawQuery("SELECT * FROM " + TABLE_ENTRIES, null);

        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.
                entriesList.add(new JournalModal(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return entriesList;
    }


    public void deleteEntry(String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete the entry with the specified ID
        db.delete(TABLE_ENTRIES, COLUMN_DATE + " = ?", new String[]{String.valueOf(date)});

        // Close the database connection
        db.close();

    }
}

