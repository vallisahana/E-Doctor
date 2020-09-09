package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class ViewFeedbackActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DatabaseFeedback dbh;
    Cursor res;
    Feed_ListAdapter la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        listView=findViewById(R.id.detailistviewfeed);

        dbh = new DatabaseFeedback(getApplicationContext());
        sqLiteDatabase=dbh.getReadableDatabase();
        res=dbh.FeedbackData();
        la=new Feed_ListAdapter(getApplicationContext(),R.layout.feed_row);
        listView.setAdapter(la);

        if(res.moveToFirst())
        {
            do{
                String examname,examdate,rate;

                examname= res.getString(0);
                examdate=res.getString(1);



               Datapovider_Feed DPC= new Datapovider_Feed(examname,examdate);
                la.add(DPC);
            }while(res.moveToNext());
        }
    }
}
