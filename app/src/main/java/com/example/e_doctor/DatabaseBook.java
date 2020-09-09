package com.example.e_doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseBook extends SQLiteOpenHelper {

    public static final String databasename="Book";

    public static final String User_table="Book_table";
    public static final int versioncode=1;

    public static final String Pat_name="Name";
    public static final String Pat_date="Date";
    public static final String Pat_time="Time";
    public static final String Pat_problem="Problem";
    public static final String Pat_phone="Phone";
    public static final String Pat_docname="docname";
    public static final String Pat_docphone="docphone";

    public DatabaseBook(Context context){
        super(
                context,
                databasename,
                null,
                versioncode);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        String user_query;
        user_query="CREATE TABLE IF NOT EXISTS "+User_table+"(Name TEXT PRIMARY KEY,Date TEXT ,Time TEXT,Problem TEXT,Phone TEXT,docname TEXT,docphone TEXT)";
        database.execSQL(user_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String user_query;
        user_query = "DROP TABLE IF EXISTS " + User_table;
                db.execSQL(user_query);

    }

    public boolean Book_Data(String name,String date,String time,String problem,String mobile,String docname,String docphone){
        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(Pat_name,name);
        cv.put(Pat_date,date);
        cv.put(Pat_time,time);
        cv.put(Pat_problem,problem);
        cv.put(Pat_phone,mobile);
        cv.put(Pat_docname,docname);
        cv.put(Pat_docphone,docphone);
        long result=db1.insert(User_table,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor BookData()
    {
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor res = db1.rawQuery("select * from "+User_table,null);
        return res;
    }

    public Cursor searchClient(String name) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor res = db2.rawQuery("select * from "+User_table+" Where name=?",new String[]{name});
        return res;
    }

    public Cursor DeleteClient(String name) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor res = db2.rawQuery("delete from "+User_table+" Where name=?",new String[]{name});
        return res;
    }


}
