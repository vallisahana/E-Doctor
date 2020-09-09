package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    public void userdetail(View view){
        Intent intent=new Intent(AdminHomeActivity.this,ViewUserDetailsActivity.class);
        startActivity(intent);
    }

    public void doctordetail(View view){
        Intent intent=new Intent(AdminHomeActivity.this,ViewDoctorDetailsActivity.class);
        startActivity(intent);
    }

    public void doctorprofile(View view){
        Intent intent=new Intent(AdminHomeActivity.this,AdminViewDoctorActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {

        Intent intent=new Intent(AdminHomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
