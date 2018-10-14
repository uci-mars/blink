package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        // Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        String[] user_and_pass = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
//
//        // Capture the layout's TextView and set the string as its text
//        TextView UsertextView = findViewById(R.id.user_display);
//        TextView PasstextView = findViewById(R.id.pass_display);
//        UsertextView.setText(user_and_pass[0]);
//        PasstextView.setText(user_and_pass[1]);
    }
}
