package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.joanzapata.pdfview.PDFView;

public class PermenKP13 extends AppCompatActivity {
    PDFView pdfu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permen_kp13);
        pdfu = (PDFView) findViewById(R.id.pdf_permenkp13);
        pdfu.fromAsset("7b135-permen-kp-13-tahun-2021_230115_120539.pdf")
                .swipeVertical(true)
                .load();

    }
}