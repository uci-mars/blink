package com.example.myapplication;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class NewAccountCreation extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmailID, editTextPassID;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account_creation);
        editTextEmailID = (EditText) findViewById(R.id.emailID);
        editTextPassID = (EditText) findViewById(R.id.passID);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signUpButton).setOnClickListener(this);
    }

    private void registerUser() {
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

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.signUpButton:
                registerUser();
                break;
        }
    }


}
