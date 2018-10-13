package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textViewSignUp).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.textViewSignUp:
                startActivity(new Intent(this, NewAccountCreation.class));
                break;
        }
    }

    /** Called when the user taps the Login button */
    public void loginClicked(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editTextUser = (EditText) findViewById(R.id.userID);
        EditText editTextPass = (EditText) findViewById(R.id.passwordID);
        String username = editTextUser.getText().toString();
        String password = editTextPass.getText().toString();
        String[] user_and_pass = {username, password};

        intent.putExtra(EXTRA_MESSAGE, user_and_pass);
        startActivity(intent);
    }
}
