package com.example.e_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class ListviewDetailsActivity extends AppCompatActivity {

    TextView textname, textfees,txtspe,textphone;
    ImageView imgphone, imgmessage, imglocation, imgfeedback;
    Button btnbook;

    SQLiteDatabase sqLiteDatabase;
    DatabaseDoctor dbh;
    Cursor res;

    String location, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_details);

        textname = findViewById(R.id.doctorname);
        textfees = findViewById(R.id.fees);
        textphone=findViewById(R.id.doctorphone);
        imgphone = findViewById(R.id.imagephone);
        imgmessage = findViewById(R.id.imagemessage);
        imglocation = findViewById(R.id.imagelocation);
        imgfeedback = findViewById(R.id.imagefeedback);
        txtspe=findViewById(R.id.doctorspeclist);
        btnbook=findViewById(R.id.bu_start_search);


        Intent intent=getIntent();
        Datapovider_Exam cust=(Datapovider_Exam) intent.getSerializableExtra("bundle");
        textname.setText(cust.getDocname());
        textfees.setText(cust.getDocfees());
        txtspe.setText(cust.getDocspec());
        textphone.setText(cust.getDocphone());

        dbh = new DatabaseDoctor(getApplicationContext());
        sqLiteDatabase = dbh.getReadableDatabase();
        res = dbh.ExamData();

        if (res.moveToFirst()) {
            do {
                String docname, fees;


                docname = res.getString(0);
                fees = res.getString(4);
                location = res.getString(3);
                phone = res.getString(5);


              // textname.setText(docname);
              //  textfees.setText(fees);


            } while (res.moveToNext());
        }

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textToPass = textname.getText().toString();
                String phone=textphone.getText().toString();

                Intent intent=new Intent(ListviewDetailsActivity.this,BookActivity.class);
                intent.putExtra("docname",textToPass);
                intent.putExtra("phone",phone);

                startActivity(intent);
            }
        });

        imgphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:7019920363"));
                startActivity(i);
                }
        });

        imgmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMessage();
            }
        });


        imgfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textToPass = textname.getText().toString();

                Intent intent=new Intent(ListviewDetailsActivity.this,FeedbackActivity.class);
                intent.putExtra("docname",textToPass);
                startActivity(intent);
            }
        });

        imglocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + location);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });


    }

    private void imagemessage(View view){

        int permissioncheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissioncheck== PackageManager.PERMISSION_GRANTED){
            MyMessage();

        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);

        }


    }

    private void MyMessage() {

      //  String phonenumber=phone.getText().toString().trim();
      //  String message=emessage.getText().toString().trim();

      //  if(!ephone.getText().toString().equals("")|| !emessage.getText().toString().equals("")) {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("7019920363", null, "Can i Get the Advance Appointement", null, null);

            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();

            //Toast.makeText(this, "please enter number or message", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }
                else {
                    Toast.makeText(this, "you dont have persmission", Toast.LENGTH_SHORT).show();
                }
        }

    }
}
