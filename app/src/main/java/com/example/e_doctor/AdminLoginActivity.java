package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    EditText email, pass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        email = (EditText) findViewById(R.id.editText3);
        pass = (EditText) findViewById(R.id.editText5);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("Admin") && pass.getText().toString().equals("admin123")) {
                    Intent in = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                    startActivity(in);
                    finish();
                } else {

                    Toast.makeText(AdminLoginActivity.this, "Invalid Credentials..!", Toast.LENGTH_LONG).show();
                }


            }


        });
    }
}
