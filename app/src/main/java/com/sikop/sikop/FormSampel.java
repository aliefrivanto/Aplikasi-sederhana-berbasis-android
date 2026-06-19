package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FormSampel extends AppCompatActivity {
    WebView a;
    WebSettings b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sampel);

        a = (WebView) findViewById(R.id.web9);
        b = a.getSettings();

        a.setWebViewClient(new WebViewClient());
        a.getSettings().setJavaScriptEnabled(true);
        a.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSc-NcchT53ztGpxF-6oAWBkLeZi9AL9srwkHrzVIMyDb_WYug/viewform?usp=sf_link");

    }
}