package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.joanzapata.pdfview.PDFView;

public class perbkipm82 extends AppCompatActivity {
    PDFView pdfu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perbkipm82);
        pdfu = (PDFView) findViewById(R.id.pdf_perbkipm82);
        pdfu.fromAsset("perbkipm82.pdf")
                .swipeVertical(true)
                .load();

    }
}