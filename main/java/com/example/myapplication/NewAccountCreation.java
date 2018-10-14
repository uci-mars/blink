package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewAccountCreation extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmailID, editTextPassID, editTextName, editTextPhone;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account_creation);

        editTextEmailID = (EditText) findViewById(R.id.emailID);
        editTextPassID = (EditText) findViewById(R.id.passID);
        editTextName = (EditText) findViewById(R.id.nameText);
        editTextPhone = (EditText) findViewById(R.id.phoneText);


        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signUpButton).setOnClickListener(this);
        System.out.println("FINISHED SETTING UP");
    }

    private void registerUser() {
        final String email = editTextEmailID.getText().toString().trim();
        final String password = editTextPassID.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();


        if(name.isEmpty()) {
            editTextEmailID.setError("Name is required");
            editTextEmailID.requestFocus();
        }

        if(phone.isEmpty()) {
//            phone = "";
            editTextEmailID.setError("Phone is required");
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
                    Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_LONG).show();

                    String id = databaseReference.push().getKey();
                    UserInformation userInfo = new UserInformation(name, id, phone);

                    mAuth.signInWithEmailAndPassword(email, password);
                    FirebaseUser user = mAuth.getCurrentUser();
                    databaseReference.child(user.getUid()).setValue(userInfo);

                    startActivity(new Intent(NewAccountCreation.this, HomePageActivity.class));
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
