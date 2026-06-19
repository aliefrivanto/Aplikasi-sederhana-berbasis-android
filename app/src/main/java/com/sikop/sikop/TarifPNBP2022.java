package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.joanzapata.pdfview.PDFView;

public class TarifPNBP2022 extends AppCompatActivity {
    PDFView pdfu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif_pnbp2022);

        pdfu = (PDFView) findViewById(R.id.pdf_uu);
        pdfu.fromAsset("Tarif PNBP ((90x170 cm)1.pdf")
                .swipeVertical(true)
                .load();
    }
}