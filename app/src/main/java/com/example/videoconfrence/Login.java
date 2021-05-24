package com.example.videoconfrence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText emailBox, passwordBox;
    Button loginBtn, signupBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailBox = findViewById(R.id.emailBox);

        passwordBox = findViewById(R.id.passwordBox);

        auth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.loginbtn);
        signupBtn = findViewById(R.id.createBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, pass;
                email = emailBox.getText().toString();
                pass = passwordBox.getText().toString();

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"Loginned",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,Dashboard.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this,"failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });
    }
}