package com.mintoo.sovereign.elite;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
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
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDatabaseEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.loadDataWithBaseURL(null, getOfflineHTML(), "text/html", "UTF-8", null);
            }
        });
        if (isNetworkAvailable(this)) {
            webView.loadUrl("https://awaismintoo.github.io/sovereign/");
        } else {
            webView.loadDataWithBaseURL(null, getOfflineHTML(), "text/html", "UTF-8", null);
        }
        setContentView(webView);
    }
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
            return nc != null && (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }
        return false;
    }
    private String getOfflineHTML() {
        return "<html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
               "<title>MintooSuite Sovereign Elite - Offline Secure Vault</title>" +
               "<style>body{background:#0a0f1d;color:#00ffcc;font-family:sans-serif;padding:20px;text-align:center;}" +
               ".card{background:#111927;border:1px solid #00ffcc;border-radius:12px;padding:20px;margin-top:20px;box-shadow:0 0 20px rgba(0,255,204,0.2);}" +
               "h1{font-size:22px;color:#fff;} p{font-size:14px;color:#a0aec0;}" +
               ".badge{background:#00ffcc;color:#0a0f1d;padding:6px 12px;border-radius:20px;font-weight:bold;display:inline-block;margin-top:10px;}" +
               "</style></head><body>" +
               "<h1>MintooSuite Sovereign Elite</h1>" +
               "<p>Autonomous Institutional Grade Terminal</p>" +
               "<div class='card'>" +
               "<h3>Offline Secured Mode Active</h3>" +
               "<p>Local SQLite Vault & Caches Loaded Successfully.</p>" +
               "<p><strong>Patent & Copyright Holder:</strong><br>Awais Rajpoot<br>awaisrajpoot2@yahoo.com</p>" +
               "<p><strong>Registered Address:</strong><br>Broadway Street, Greens Avenue, Park Road, Islamabad, Pakistan</p>" +
               "<div class='badge'>Certified & Signed</div>" +
               "</div></body></html>";
    }
}
