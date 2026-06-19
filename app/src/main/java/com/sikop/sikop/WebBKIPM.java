package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebBKIPM extends AppCompatActivity {
    WebView a;
    WebSettings b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_bkipm);
        a = (WebView) findViewById(R.id.web1);
        b = a.getSettings();

        a.setWebViewClient(new WebViewClient());
        a.getSettings().setJavaScriptEnabled(true);
        a.loadUrl("https://kkp.go.id/bkipm/stasiunkipmlampung");
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