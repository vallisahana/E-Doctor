package com.example.e_doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseFeedback extends SQLiteOpenHelper {

    public static final String databasename = "fdb";

    public static final String Feed_table = "Feed_table";
    public static final int versioncode = 1;

    public static final String User_id = "id";
    public static final String User_name = "Name";
    public static final String User_feedback = "feedback";



    public DatabaseFeedback(Context context) {
        super(
                context,
                databasename,
                null,
                versioncode);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        String user_query;
        user_query = "CREATE TABLE IF NOT EXISTS " + Feed_table + "(id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT ,feedback TEXT)";
        database.execSQL(user_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String user_query;
        user_query = "DROP TABLE IF EXISTS " + Feed_table;
        db.execSQL(user_query);

    }

    public boolean Feedback_Data(String name, String feedback) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(User_name, name);
        cv.put(User_feedback, feedback);

        long result = db1.insert(Feed_table, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor FeedbackData() {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor res = db1.rawQuery("select * from " + Feed_table, null);
        return res;
    }

    public Cursor fetchdata(String feedback) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor res = db2.rawQuery("select * from "+Feed_table+" Where feedback=?",new String[]{feedback});
        return res;
    }

}
