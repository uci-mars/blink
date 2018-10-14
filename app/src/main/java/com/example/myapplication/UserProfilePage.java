package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserProfilePage extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    private TextView textViewName;
    private ListView mListView;


    ImageView profilePicture;
//    @tools:sample/avatars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        mListView = (ListView) findViewById(R.id.listview);
        textViewName = (TextView) findViewById(R.id.textViewName);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        System.out.println("USER ID: " + userID);

        profilePicture = (ImageView) findViewById(R.id.imageViewPicture);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            UserInformation uInfo = new UserInformation();
            uInfo.setUserName(ds.child(userID).child("userName").getValue(String.class));
            uInfo.setPhone(ds.child(userID).child("phone").getValue(String.class));
            uInfo.setEmail(ds.child(userID).child("email").getValue(String.class));
//            uInfo.setUserName(ds.child(userID).getValue(UserInformation.class).getUserName());
//            uInfo.setPhone(ds.child(userID).getValue(UserInformation.class).getPhone());
//            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail());
//
            ArrayList<String> array = new ArrayList<>();
            array.add(uInfo.getPhone());
            array.add(uInfo.getEmail());

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);
            textViewName.setText(uInfo.getUserName());

        }
    }
}
