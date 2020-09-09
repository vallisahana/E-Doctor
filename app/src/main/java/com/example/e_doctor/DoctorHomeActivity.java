package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorHomeActivity extends AppCompatActivity {

    EditText editexamname,editexamdate,editexamtime,editexamvenue,editfees,editphone;
    Button button,buttonrecord;

    DatabaseDoctor DH = new DatabaseDoctor(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);


        editexamname=findViewById(R.id.editexamname);
        editexamdate=findViewById(R.id.editexamdate);
        editexamtime=findViewById(R.id.editexamtime);
        editexamvenue=findViewById(R.id.editexamvenu);
        editfees=findViewById(R.id.editfees);
        editphone=findViewById(R.id.editphone);
        button=findViewById(R.id.btnexamupdate);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(editexamname.getText().toString()) ||
                        TextUtils.isEmpty(editexamdate.getText().toString()) ||
                        TextUtils.isEmpty(editexamtime.getText().toString())||
                        TextUtils.isEmpty(editexamvenue.getText().toString())){

                    Toast.makeText(DoctorHomeActivity.this, "Pls fill the fields",
                            Toast.LENGTH_LONG).show();


                } else {

                    boolean res = DH.Doctor_Data(editexamname.getText().toString(),editexamdate.getText().toString(),
                            editexamtime.getText().toString(),editexamvenue.getText().toString(),editfees.getText().toString(),editphone.getText().toString());
                    if (res) {

                        Toast.makeText(DoctorHomeActivity.this, "Submitted Successfully",
                                Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(DoctorHomeActivity.this,DoctorMainActivity.class);
                        startActivity(intent);
                        finish();

                        editexamvenue.setText("");
                        editexamtime.setText("");
                        editexamdate.setText("");
                        editexamname.setText("");
                        editfees.setText("");
                        editphone.setText("");

                    }
                }
            }

        });
    }
}
