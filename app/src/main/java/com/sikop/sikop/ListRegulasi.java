package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ListRegulasi extends AppCompatActivity implements View.OnClickListener {
    ImageView tofile1;
    TextView a,b,c,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_regulasi);

        a = (TextView) findViewById(R.id.link1);
        a.setOnClickListener(this);

        b = (TextView) findViewById(R.id.link2);
        b.setOnClickListener(this);

        c = (TextView) findViewById(R.id.link3);
        c.setOnClickListener(this);

        tofile1 = (ImageView) findViewById(R.id.kembali);
        tofile1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kembali:
                startActivity(new Intent(this, File1.class));
                break;
            case R.id.link1:
                startActivity(new Intent(this, UU21.class));
                break;
            case R.id.link2:
                startActivity(new Intent(this, PermenKP16.class));
                break;
            case R.id.link3:
                startActivity(new Intent(this, perbkipm82.class));
                break;
        }
    }
}