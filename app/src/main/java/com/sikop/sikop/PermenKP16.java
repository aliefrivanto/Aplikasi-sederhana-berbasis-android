package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.joanzapata.pdfview.PDFView;

public class PermenKP16 extends AppCompatActivity {
    PDFView pdfu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permen_kp16);

        pdfu = (PDFView) findViewById(R.id.pdf_permenkp16);
        pdfu.fromAsset("AUTENTIKASI PERMEN KP NO 16 TAHUN 2022.pdf")
                .swipeVertical(true)
                .load();

    }
}