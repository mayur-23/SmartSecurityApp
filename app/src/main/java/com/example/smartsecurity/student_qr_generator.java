package com.example.smartsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class student_qr_generator extends AppCompatActivity {

    EditText etInput;
    Button btnGenerate;
    ImageView ImgOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_qr_generator);
        etInput=findViewById(R.id.et_input);
        btnGenerate=findViewById(R.id.btn_generate);
        ImgOutput=findViewById(R.id.img_output);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText=etInput.getText().toString().trim();
                QRGEncoder qrgEncoder = new QRGEncoder(sText, null, QRGContents.Type.TEXT, 100);
                qrgEncoder.setColorBlack(Color.rgb(37,59,117));
                qrgEncoder.setColorWhite(Color.WHITE);
                // Getting QR-Code as Bitmap
                Bitmap bitmap = qrgEncoder.getBitmap();
                // Setting Bitmap to ImageView
                ImgOutput.setImageBitmap(bitmap);

            }
        });

    }
}