package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    FirebaseAuth mAuth;
    EditText editTextEmailID, editTextPassID, textViewNFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.textViewSignUp).setOnClickListener(this);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.loginButtonGoogle).setOnClickListener(this);
    }

    public void userLogin() {
        editTextEmailID = (EditText) findViewById(R.id.userID);
        editTextPassID = (EditText) findViewById(R.id.passwordID);

        String email = editTextEmailID.getText().toString().trim();
        String password = editTextPassID.getText().toString().trim();

        if(email.isEmpty()) {
            editTextEmailID.setError("Email is required");
            editTextEmailID.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailID.setError("Please enter a valid email");
            editTextEmailID.requestFocus();
        }

        if(password.isEmpty()) {
            editTextEmailID.setError("Password is required");
            editTextEmailID.requestFocus();
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent i = new Intent(MainActivity.this, HomePageActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //so user cant go back after logging in
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.textViewSignUp:
                startActivity(new Intent(this, NewAccountCreation.class));
                break;
            case R.id.loginButton:
                userLogin();
                break;
            case R.id.loginButtonGoogle:
                startActivity(new Intent(this, SendNfcIntent.class));
                break;
        }
    }
}
