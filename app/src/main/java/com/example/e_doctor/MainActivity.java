package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnpatient,btndoctor,btnadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnpatient=findViewById(R.id.patient);
        btndoctor=findViewById(R.id.doctor);
        btnadmin=findViewById(R.id.admin);


        btnpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, UserLoginActivity.class );

                startActivity( intent );
            }
        });

        btndoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, DoctorLoginActivity.class );

                startActivity( intent );
            }
        });

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, AdminLoginActivity.class );

                startActivity( intent );
            }
        });
    }
}
