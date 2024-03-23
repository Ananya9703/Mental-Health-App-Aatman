package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    Button signupbtn, signinbtn;
    EditText uemail,upwd;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        uemail=findViewById(R.id.email1);
        upwd=findViewById(R.id.pwd1);
        signupbtn=findViewById(R.id.signup2);
        signinbtn=findViewById(R.id.signin2);
        dbHandler = new DBHandler(this);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signin.this, signup.class);
                startActivity(i);
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMail = uemail.getText().toString();
                String userPwd = upwd.getText().toString();

                if(userMail.isEmpty() || userPwd.isEmpty()){
                    Toast.makeText(signin.this, "Please enter all required values", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbHandler.checkUser(userMail,userPwd)){
                    // Successful login, move to a new activity
                    Intent intent = new Intent(signin.this, landingpg.class);
                    startActivity(intent);

                } else {
                    // Show a toast for invalid login
                    Toast.makeText(signin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}