package com.example.smartsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Qrscanner extends AppCompatActivity {

    Button qrbtn;
    public static TextView qrtext;
    TextView titletxt;
    Button formbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        qrbtn=(Button)findViewById(R.id.qrbtn);
        formbtn=(Button)findViewById(R.id.formbtn);
        qrtext=(TextView)findViewById(R.id.qrtxt);
        titletxt=(TextView)findViewById(R.id.qrscanner_title);

        Bundle b=getIntent().getExtras();
        if(b!=null){
            String temp = (String)b.get("title");
            titletxt.setText(temp);

        }

        qrbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(), ScannerView.class);
                intent.putExtra("DBname",titletxt.getText().toString());
                startActivity(intent);

            }
        });

        formbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), form.class);
                startActivity(intent);

            }
        });

    }
}