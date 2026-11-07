package com.mrakempf.pencilandpuzzle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Shows the Pencil & Puzzle web app full screen. It loads the GitHub Pages site,
 * so the app picks up every update without a new APK. When the phone is offline
 * it falls back to the last copy the app saw.
 */
public class MainActivity extends Activity {
    private static final String HOME = "https://mrakempf.github.io/pencil-and-puzzle/";
    private WebView web;
    private boolean triedCache = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        web = new WebView(this);
        setContentView(web);

        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();
                if (url.toString().startsWith(HOME)) return false;
                // Links to other sites open in the phone's browser
                startActivity(new Intent(Intent.ACTION_VIEW, url));
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (!request.isForMainFrame()) return;
                if (!triedCache) {
                    triedCache = true;
                    view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    view.loadUrl(HOME);
                } else {
                    view.loadData("<html><body style='font-family:sans-serif;padding:32px;text-align:center'>"
                            + "<h2>No internet connection</h2><p>Pencil &amp; Puzzle needs the internet the first time it opens. "
                            + "Connect and reopen the app.</p></body></html>", "text/html", "utf-8");
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.startsWith(HOME)) {
                    triedCache = false;
                    view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                }
            }
        });

        if (savedInstanceState != null) {
            web.restoreState(savedInstanceState);
        } else {
            web.loadUrl(HOME);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        web.saveState(outState);
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
