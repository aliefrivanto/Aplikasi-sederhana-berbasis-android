package com.sikop.sikop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WA extends AppCompatActivity {
    WebView a;
    WebSettings b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wa);

        a = (WebView) findViewById(R.id.s5);
        b = a.getSettings();

        a.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if( URLUtil.isNetworkUrl(url) ) {
                    return false;
                }
                if (appInstalledOrNot(url)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity( intent );
                } else {
                    // do something if app is not installed
                }
                return true;
            }
        });
        a.getSettings().setJavaScriptEnabled(true);
        a.getSettings().setAllowUniversalAccessFromFileURLs(true);
        a.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        a.getSettings().getDomStorageEnabled();
        a.loadUrl("https://api.whatsapp.com/send/?phone=62816245342&text=Hi%20BKIPM%20Lampung&type=phone_number&app_absent=0");
    }

    private boolean appInstalledOrNot(String url) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {

        }

        return false;
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
