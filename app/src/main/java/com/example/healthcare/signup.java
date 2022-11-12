package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    EditText Name,Email,Password,Repassword;
    Button Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name=findViewById(R.id.name);
        Email=findViewById(R.id.email_edt_text);
        Password=findViewById(R.id.pass_edt_text);
        Repassword=findViewById(R.id.repass);
        Signup=findViewById(R.id.signup_btn);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String pass = Password.getText().toString();
                String repass = Repassword.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
                    Toast.makeText(getApplicationContext(), "All fields are required...", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Registration successful👍\nredirecting to login page...", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Passwords not matching...", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
}