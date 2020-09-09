package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorRegisterActivity extends AppCompatActivity {

    EditText editTextname,editTextemail,editTextpassword,editTextphone;
    Button buttonuseregister;
    TextView textViewuserlogin;

    DatabaseDoc DH = new DatabaseDoc(this);

    CheckBox chk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);


        editTextname=findViewById(R.id.editname);
        editTextemail=findViewById(R.id.editemail);
        editTextpassword=findViewById(R.id.editpassword);
        editTextphone=findViewById(R.id.editphone);
        buttonuseregister=findViewById(R.id.buttonregister);
        textViewuserlogin=findViewById(R.id.textsign);
        chk = (CheckBox) findViewById(R.id.checkregister);

        textViewuserlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DoctorRegisterActivity.this, DoctorLoginActivity
                        .class);
                startActivity(in);
            }
        });

        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {
                    editTextpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });





        buttonuseregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextname.getText().toString()) ||
                        TextUtils.isEmpty(editTextemail.getText().toString()) ||
                        TextUtils.isEmpty(editTextpassword.getText().toString())||
                        TextUtils.isEmpty(editTextphone.getText().toString())){
                    Toast.makeText(DoctorRegisterActivity.this, "Pls fill the fields",
                            Toast.LENGTH_LONG).show();

                } else {
                    int mobile = editTextphone.length();
                    String name = editTextname.getText().toString();
                    String email = editTextemail.getText().toString();
                    String password=editTextphone.getText().toString();


                    if (mobile > 10 || mobile < 10) {
                        Toast.makeText(DoctorRegisterActivity.this, "Invalid Mobile Number",
                                Toast.LENGTH_LONG).show();
                    }
                    if(password.length()<6){
                        Toast.makeText(DoctorRegisterActivity.this, "Password should be length of 6 or more than 6",
                                Toast.LENGTH_LONG).show();
                    }
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Toast.makeText(DoctorRegisterActivity.this, "Inavild Email",
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        boolean res = DH.User_Data(editTextname.getText().toString(),
                                editTextemail.getText().toString(),editTextpassword.getText().toString(),
                                editTextphone.getText().toString());
                        if (res) {

                            Toast.makeText(DoctorRegisterActivity.this, "Registered Successfully",
                                    Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(DoctorRegisterActivity.this,DoctorLoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(DoctorRegisterActivity.this, "Try Again",
                                    Toast.LENGTH_LONG).show();
                        }

                        editTextemail.setText("");
                        editTextphone.setText("");
                        editTextpassword.setText("");
                        editTextname.setText("");
                    }
                }


            }
        });

    }
}
