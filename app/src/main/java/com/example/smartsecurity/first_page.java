package com.example.smartsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class first_page extends AppCompatActivity {

    Button btn_first;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        auth=FirebaseAuth.getInstance();
        btn_first = (Button) findViewById(R.id.btn_firstlogin);
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login_type_select.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =auth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }
}