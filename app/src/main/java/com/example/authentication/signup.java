package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    Button signupbtn, signinbtn;
    EditText uemail,uname,upwd;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        uemail=findViewById(R.id.email);
        uname=findViewById(R.id.name);
        upwd=findViewById(R.id.pwd);
        signupbtn=findViewById(R.id.signup1);
        signinbtn=findViewById(R.id.signin1);
        dbHandler = new DBHandler(this);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=uname.getText().toString();
                String userMail=uemail.getText().toString();
                String userPwd=upwd.getText().toString();

                if(userName.isEmpty() || userMail.isEmpty() || userPwd.isEmpty()){
                    Toast.makeText(signup.this, "Please enter all required values", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.addUser(userName, userMail, userPwd);
                Toast.makeText(signup.this,"User has been added", Toast.LENGTH_SHORT).show();
                uname.setText("");
                uemail.setText("");
                upwd.setText("");

                Intent i=new Intent(signup.this, landingpg.class);
                startActivity(i);
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup.this, signin.class);
                startActivity(i);
            }
        });
    }
}