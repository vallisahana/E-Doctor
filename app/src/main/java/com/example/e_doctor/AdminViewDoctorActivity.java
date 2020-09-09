package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class AdminViewDoctorActivity extends AppCompatActivity {


    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DatabaseDoctor dbh;
    Cursor res;
    Exam_ListAdapter la;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_doctor);

        listView=findViewById(R.id.detailistviewdoc);

        dbh = new DatabaseDoctor(getApplicationContext());
        sqLiteDatabase=dbh.getReadableDatabase();
        res=dbh.ExamData();
        la=new Exam_ListAdapter(getApplicationContext(),R.layout.exam_row);
        listView.setAdapter(la);

        if(res.moveToFirst())
        {
            do{
                String examname,examdate,examtime,examvenue,fee,phone;

                examname= res.getString(0);
                examdate=res.getString(1);
                examtime=res.getString(2);
                examvenue=res.getString(3);
                fee=res.getString(4);
                phone=res.getString(5);

                Datapovider_Exam DPC= new Datapovider_Exam(examname,examdate,examtime,examvenue,fee,phone);
                la.add(DPC);
            }while(res.moveToNext());
        }
    }
}
