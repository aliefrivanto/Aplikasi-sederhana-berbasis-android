package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FormKuisoner extends AppCompatActivity {
    WebView a;
    WebSettings b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kuisoner);
        a = (WebView) findViewById(R.id.web10);
        b = a.getSettings();

        a.setWebViewClient(new WebViewClient());
        a.getSettings().setJavaScriptEnabled(true);
        a.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdwWnLlEOV82P8_EWdhshqjtXPM-szn0eJw5obrQggT01CzHw/viewform?usp=sf_link");

    }
}