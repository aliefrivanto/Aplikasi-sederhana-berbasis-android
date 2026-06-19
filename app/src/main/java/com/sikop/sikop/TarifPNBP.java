package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TarifPNBP extends AppCompatActivity implements View.OnClickListener {
    ImageView tofile1;
    TextView pnbp2022;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif_pnbp);
        tofile1 = (ImageView) findViewById(R.id.kembali);
        tofile1.setOnClickListener(this);

        pnbp2022 = (TextView) findViewById(R.id.pnbp1);
        pnbp2022.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.kembali:
                startActivity(new Intent(this, File1.class));
                break;
            case R.id.pnbp1:
                startActivity(new Intent(this, TarifPNBP2022.class));
                break;
        }
    }
}