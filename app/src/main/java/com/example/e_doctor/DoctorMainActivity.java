package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
    }

    public void AddDetails(View view){
        Intent intent=new Intent(DoctorMainActivity.this,DoctorHomeActivity.class);
        startActivity(intent);
    }

    public void viewfeedback(View view){
        Intent intent=new Intent(DoctorMainActivity.this,ViewFeedbackActivity.class);
        startActivity(intent);
    }
    public void viewappointment(View view){
        Intent intent=new Intent(DoctorMainActivity.this,ViewBookActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent=new Intent(DoctorMainActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
