package com.mintoo.sovereign.elite;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView sovereignWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sovereignWebView = new WebView(this);
        setContentView(sovereignWebView);

        WebSettings webSettings = sovereignWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        sovereignWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        sovereignWebView.loadUrl("file:///android_asset/index.html");
        Toast.makeText(this, "MintooSuite Sovereign Elite Initialized [Awais Rajpoot]", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (sovereignWebView.canGoBack()) {
            sovereignWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
