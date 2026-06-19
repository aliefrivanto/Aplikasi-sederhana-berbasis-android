package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Twitter extends AppCompatActivity {
    WebView a;
    WebSettings b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        a = (WebView) findViewById(R.id.s2);
        b = a.getSettings();

        a.setWebViewClient(new WebViewClient());
        a.getSettings().setJavaScriptEnabled(true);
        a.loadUrl("https://twitter.com/LampungBkipm");
    }
    @Override
    public void onBackPressed() {
        if (a.canGoBack()){
            a.goBack();
        }else {
            finish();
        }
    }
}