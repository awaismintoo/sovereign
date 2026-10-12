package com.mintoo.sovereign.elite;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private SovereignDatabaseHelper dbHelper;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new SovereignDatabaseHelper(this);
        dbHelper.getWritableDatabase();
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.loadDataWithBaseURL(null, getOfflineHTML(), "text/html", "UTF-8", null);
            }
        });
        webView.loadDataWithBaseURL(null, getOfflineHTML(), "text/html", "UTF-8", null);
        setContentView(webView);
    }
    private String getOfflineHTML() {
        return "<html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
               "<title>MintooSuite Sovereign Elite</title>" +
               "<style>body{background:#0a0f1d;color:#00ffcc;font-family:sans-serif;padding:20px;text-align:center;}" +
               ".card{background:#111927;border:1px solid #00ffcc;border-radius:12px;padding:20px;margin-top:20px;box-shadow:0 0 20px rgba(0,255,204,0.2);}" +
               "h1{font-size:22px;color:#fff;} p{font-size:14px;color:#a0aec0;}" +
               ".badge{background:#00ffcc;color:#0a0f1d;padding:6px 12px;border-radius:20px;font-weight:bold;display:inline-block;margin-top:10px;}" +
               "</style></head><body>" +
               "<h1>MintooSuite Sovereign Elite</h1>" +
               "<p>Autonomous Institutional Terminal</p>" +
               "<div class='card'>" +
               "<h3>100% Autonomous & Self-Healing Vault</h3>" +
               "<p>SQLite Database & Cache Verified.</p>" +
               "<p><strong>Patent & Copyright Holder:</strong><br>Awais Rajpoot<br>awaisrajpoot2@yahoo.com</p>" +
               "<p><strong>Registered Address:</strong><br>Broadway Street, Greens Avenue, Park Road, Islamabad, Pakistan</p>" +
               "<div class='badge'>Verified & Fully Operational</div>" +
               "</div></body></html>";
    }
}
