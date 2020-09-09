package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class UserHomeActivity extends AppCompatActivity {


    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DatabaseDoctor dbh;
    Cursor res;
    Exam_ListAdapter la;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        listView=findViewById(R.id.detailistview);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for(int i=0;i<parent.getChildCount();i++){

                    if(i==position){
                        parent.getChildAt(i).setBackgroundColor(Color.GREEN);
                    }else if(i<=position)
                    {
                        parent.getChildAt(i).setBackgroundColor(Color.RED);
                    }
                    else if(i>=position){
                        parent.getChildAt(i).setBackgroundColor(Color.GREEN);
                    }else{
                        parent.getChildAt(i).setBackgroundColor(Color.RED);
                    }
                }

                Datapovider_Exam dpc=(Datapovider_Exam)parent.getAdapter().getItem(position);

                dpc.getDocname();
                dpc.getDocfees();
                dpc.getDocspec();
                dpc.getDocphone();


                Intent intent=new Intent(UserHomeActivity.this,ListviewDetailsActivity.class);
                intent.putExtra("bundle", dpc);
                startActivity(intent);
            }
        });
    }
}
