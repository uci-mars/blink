package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{

    Button gotoUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViewById(R.id.gotoUpdate).setOnClickListener(this);
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
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.gotoUpdate:
                startActivity(new Intent(this, UpdateProfile.class));
                break;
        }
    }
}
