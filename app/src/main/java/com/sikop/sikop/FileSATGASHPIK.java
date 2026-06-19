package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FileSATGASHPIK extends AppCompatActivity implements View.OnClickListener {
    ImageView a;
    TextView b,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_satgashpik);
        a = (ImageView) findViewById(R.id.balek);
        a.setOnClickListener(this);

        b = (TextView) findViewById(R.id.perkp13);
        b.setOnClickListener(this);

        c = (TextView) findViewById(R.id.kusioner);
        c.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.balek:
                startActivity(new Intent(this, File1.class));
                break;
            case R.id.perkp13:
                startActivity(new Intent(this, PermenKP13.class));
                break;
            case R.id.kusioner:
                startActivity(new Intent(this, FormKuisoner.class));
                break;
        }
    }
}