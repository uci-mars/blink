package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{

    Button gotoUpdate, gotoProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViewById(R.id.gotoUpdate).setOnClickListener(this);
        findViewById(R.id.gotoProfile).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.gotoUpdate:
                startActivity(new Intent(this, UpdateProfile.class));
                break;
            case R.id.gotoProfile:
                startActivity(new Intent(this, UserProfilePage.class));
                break;
            case R.id.buttonSendNFC:
                startActivity(new Intent(this, SendNfcIntent.class));
                break;
        }
    }
}