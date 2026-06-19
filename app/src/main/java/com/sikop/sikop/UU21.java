package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.joanzapata.pdfview.PDFView;

public class UU21 extends AppCompatActivity {
    PDFView pdfu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uu21);

        pdfu = (PDFView) findViewById(R.id.pdf_uu);
        pdfu.fromAsset("UU Nomor 21 Tahun 2019.pdf")
                .swipeVertical(true)
                .load();
    }
}