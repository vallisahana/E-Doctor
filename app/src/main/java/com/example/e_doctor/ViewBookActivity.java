package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ViewBookActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
   DatabaseBook dbh;
    Cursor res;
    Book_ListAdapter la;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        listView=findViewById(R.id.detailistview);

        dbh = new DatabaseBook(getApplicationContext());
        sqLiteDatabase=dbh.getReadableDatabase();
        res=dbh.BookData();
        la=new Book_ListAdapter(getApplicationContext(),R.layout.book_row);
        listView.setAdapter(la);

        if(res.moveToFirst())
        {
            do{
                String examname,examdate,examtime,examvenue,phone,docname,docphone;

                examname= res.getString(0);
                examdate=res.getString(1);
                examtime=res.getString(2);
                examvenue=res.getString(3);
                phone=res.getString(4);
                docname=res.getString(5);
                docphone=res.getString(6);


                Datapovider_Book DPC= new Datapovider_Book(examname,examdate,examtime,examvenue,phone,docname,docphone);
                la.add(DPC);
            }while(res.moveToNext());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Datapovider_Book dpc=(Datapovider_Book) parent.getAdapter().getItem(position);
                // String name=dpc.getDocname();

                dpc.getPhone();

                Intent intent=new Intent(ViewBookActivity.this,AdminsendrequestActivity.class);
                intent.putExtra("bundle", dpc);
                startActivity(intent);
            }
        });
    }
}
