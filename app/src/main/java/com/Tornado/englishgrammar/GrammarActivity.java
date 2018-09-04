package com.Tornado.englishgrammar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class GrammarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_grammar);
        int id  = getIntent().getIntExtra("id",-1);
        WebView webView = (WebView) this.findViewById(R.id.grammar_webview);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
      //  webSettings.setUseWideViewPort(true);
        //webView.setInitialScale(2);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript("(function(){return window.getSelection().toString()})()",
                    new ValueCallback<String>()
                    {
                        @Override
                        public void onReceiveValue(String value)
                        {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(getBaseContext().CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("webView", value);
                            clipboard.setPrimaryClip(clip);
                        }
                    });
        }

        // webView.loadData("ABC", "text/html", "UTF-8");
        String url = "file:///android_asset/lesson"  + id + ".html";
        webView.loadUrl(url);
        webView.requestFocus();
    }
}
