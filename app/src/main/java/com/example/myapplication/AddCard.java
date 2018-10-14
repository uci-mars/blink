package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCard extends AppCompatActivity implements View.OnClickListener {

    EditText editTextCardID;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.buttonAddCard).setOnClickListener(this);
        editTextCardID = (EditText) findViewById(R.id.textViewCardID);
        databaseReference = FirebaseDatabase.getInstance().getReference("collection");
        mAuth = FirebaseAuth.getInstance();

    }

    private void addCard() {
        String cardID = editTextCardID.getText().toString().trim();

        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("connections").push().setValue(cardID);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonAddCard:
                addCard();
        }
    }
}
