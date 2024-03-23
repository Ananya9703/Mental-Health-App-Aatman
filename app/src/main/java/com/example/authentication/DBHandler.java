package com.example.authentication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "entrydb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "users";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our user name column
    private static final String NAME_COL = "name";

    // below variable id for our user mail column.
    private static final String MAIL_COL = "mail";

    // below variable for our user pswd column.
    private static final String PWD_COL = "pwd";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + MAIL_COL + " TEXT,"
                + PWD_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addUser(String userName, String userMail, String userPwd) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, userName);
        values.put(MAIL_COL, userMail);
        values.put(PWD_COL, userPwd);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public boolean checkUser(String enteredUsername, String enteredPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {MAIL_COL, PWD_COL};
        String selection = MAIL_COL + "=? AND " + PWD_COL + "=?";
        String[] selectionArgs = {enteredUsername, enteredPassword};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        boolean authenticated = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return authenticated;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
