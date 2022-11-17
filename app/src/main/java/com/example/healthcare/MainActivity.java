package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText Email,Password;
    Button Login,SignUp;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email= findViewById(R.id.email_edt_text);
        Password= findViewById(R.id.pass_edt_text);
        Login= findViewById(R.id.login_btn);
        SignUp= findViewById(R.id.signup_btn);



        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.show();
                String email=Email.getText().toString();
                String pass =Password.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                    mProgress.dismiss();
                    Toast.makeText(MainActivity.this, "All fields required...", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                mProgress.dismiss();
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                startActivity(intent);
                            }else{
                                mProgress.dismiss();
                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });
    }
}