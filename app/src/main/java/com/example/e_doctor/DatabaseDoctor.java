package com.example.e_doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseDoctor extends SQLiteOpenHelper {

    public static final String databasename="Doctordetails";

    public static final String Doctor_table="Doctor_table";

    public static final int versioncode=1;

    public static final String Doc_id="docid";
    public static final String Doc_name="docname";
    public static final String Doc_speclist="docspeclist";
    public static final String Doc_experiance="docexperiance";
    public static final String Doc_hospitalname="hospitalname";
    public static final String Doc_Fees="fees";
    public static final String Doc_Phone="phone";




    public DatabaseDoctor(Context context){
        super(
                context,
                databasename,
                null,
                versioncode);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        String user_query;
        user_query="CREATE TABLE IF NOT EXISTS "+Doctor_table+"(docname TEXT PRIMARY KEY,docspeclist TEXT ,docexperiance TEXT,hospitalname TEXT,fees TEXT,phone TEXT)";
        database.execSQL(user_query);

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String user_query;
        user_query = "DROP TABLE IF EXISTS " + Doctor_table;
                db.execSQL(user_query);

    }

    public boolean Doctor_Data(String docname, String docspec, String docexp, String docaddr, String fees,String phone){
        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues cv=new ContentValues();


        cv.put(Doc_name,docname);
        cv.put(Doc_speclist,docspec);
        cv.put(Doc_experiance,docexp);
        cv.put(Doc_hospitalname,docaddr);
        cv.put(Doc_Fees,fees);
        cv.put(Doc_Phone,phone);

        long result=db1.insert(Doctor_table,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor ExamData()
    {
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor res = db1.rawQuery("select * from "+Doctor_table,null);
        return res;
    }

    public Cursor searchClient(String name) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor res = db2.rawQuery("select * from "+Doctor_table+" Where name=?",new String[]{name});
        return res;
    }

    public Cursor DeleteClient(String name) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor res = db2.rawQuery("delete from "+Doctor_table+" Where name=?",new String[]{name});
        return res;
    }
}
