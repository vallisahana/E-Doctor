package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {

    EditText name,date,time,problem,phone;
    Button button;
    EditText editTextname,docphone;

    DatabaseBook DH = new DatabaseBook(this);

    SQLiteDatabase sqLiteDatabase;
    DatabaseDoctor dbh;
    Cursor res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        editTextname=findViewById(R.id.editdocname);
        docphone=findViewById(R.id.editdocphone);
        name=findViewById(R.id.editpatname);
        date=findViewById(R.id.editpatdate);
        time=findViewById(R.id.editpattime);
        problem=findViewById(R.id.editpro);
        phone=findViewById(R.id.editphone);

        button=findViewById(R.id.btnpatsubmit);


        Intent intent=getIntent();

        String text = intent.getStringExtra("docname");
        String doctorphone=intent.getStringExtra("phone");

        editTextname.setText(text);
        docphone.setText(doctorphone);

        dbh = new DatabaseDoctor(getApplicationContext());
        sqLiteDatabase = dbh.getReadableDatabase();
        res = dbh.ExamData();

        if (res.moveToFirst()) {
            do {
                String docname,phone;


                docname = res.getString(0);
                phone=res.getString(5);;

              //  editTextname.setText(docname);
              //  docphone.setText(phone);


            } while (res.moveToNext());
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(name.getText().toString()) ||
                        TextUtils.isEmpty(date.getText().toString()) ||
                        TextUtils.isEmpty(time.getText().toString())||
                        TextUtils.isEmpty(problem.getText().toString())){

                    Toast.makeText(BookActivity.this, "Pls fill the fields",
                            Toast.LENGTH_LONG).show();


                } else {

                    boolean res = DH.Book_Data(name.getText().toString(),date.getText().toString(),
                            time.getText().toString(),problem.getText().toString(),phone.getText().toString(),editTextname.getText().toString(),
                            docphone.getText().toString());
                    if (res) {

                        Toast.makeText(BookActivity.this, "Submitted Successfully",
                                Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(BookActivity.this,ListviewDetailsActivity.class);
                        startActivity(intent);
                        finish();

                        problem.setText("");
                        time.setText("");
                        date.setText("");
                        name.setText("");

                        phone.setText("");

                    }
                }
            }

        });
    }
}
