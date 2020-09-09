package com.example.e_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminsendrequestActivity extends AppCompatActivity {

    EditText editphone,editmessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsendrequest);

        Intent intent = getIntent();
        Datapovider_Book cust = (Datapovider_Book) intent.getSerializableExtra("bundle");
       // editphone.setText(cust.getPhone());



        editphone = findViewById(R.id.phonenumber);
        editmessage = findViewById(R.id.feedback);

    }

    public void btn_send(View view) {
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permissioncheck == PackageManager.PERMISSION_GRANTED) {
            MyMessage();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);

        }
    }


    private void MyMessage() {

        String phonenumber = editphone.getText().toString().trim();
        String message = editmessage.getText().toString().trim();

        if (!editphone.getText().toString().equals("") || !editmessage.getText().toString().equals("")) {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber, null, message, null, null);

            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "please enter number or message", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyMessage();
                } else {
                    Toast.makeText(this, "you dont have persmission", Toast.LENGTH_SHORT).show();
                }
        }

    }

}
