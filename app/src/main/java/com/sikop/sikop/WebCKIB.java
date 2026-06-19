package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebCKIB extends AppCompatActivity {
    WebView a;
    WebSettings b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_ckib);
        a = (WebView) findViewById(R.id.web2);
        b = a.getSettings();

        a.setWebViewClient(new WebViewClient());
        a.getSettings().setJavaScriptEnabled(true);
        a.loadUrl("http://ckib.bkipm.kkp.go.id/");
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