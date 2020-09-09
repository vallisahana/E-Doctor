package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {

    EditText editTextemail,editTextpass;
    Button buttonuserlogin;
    TextView textView;

    DatabaseUser databaseUser= new DatabaseUser(this);

    CheckBox chk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        editTextemail=findViewById(R.id.editemail);
        editTextpass=findViewById(R.id.editpassword);
        buttonuserlogin=findViewById(R.id.buttonlogin);
        textView=findViewById(R.id.textlogin);
        chk = (CheckBox) findViewById(R.id.checklogin);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( UserLoginActivity.this, UserRegisterActivity.class );

                startActivity( intent );
            }
        });


        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {
                    editTextpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        buttonuserlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editTextemail.getText().toString();
                String Password = editTextpass.getText().toString();

                Boolean chkemailpass = databaseUser.emailpassword(email, Password);

                if (chkemailpass == true) {
                    Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UserLoginActivity.this,TypeselectActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
