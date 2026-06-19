package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Maps extends AppCompatActivity implements View.OnClickListener {
    ImageView tofile;
    ImageView link1,link2,link3,link4,link5;
    ImageView lapor;
    TextView lapor1;
    TextView b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        tofile =(ImageView) findViewById(R.id.k);
        tofile.setOnClickListener(this);

        lapor = (ImageView) findViewById(R.id.laporan);
        lapor.setOnClickListener(this);
        lapor1 = (TextView) findViewById(R.id.laporan1);
        lapor1.setOnClickListener(this);

        b = (TextView) findViewById(R.id.alamt);
        b.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.k:
                startActivity(new Intent(this, File1.class));
                break;
            case R.id.laporan:
                startActivity(new Intent(this, WebLAPOR.class));
                break;
            case R.id.laporan1:
                startActivity(new Intent(this, WebLAPOR.class));
                break;

        }
    }
}