package com.anto.yourgarage.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.anto.yourgarage.R;

public class HelpActivity extends AppCompatActivity {

    private WebView myWebView;

    String help = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Ayuda");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        myWebView  = findViewById(R.id.webview);

        myWebView.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        myWebView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });
        help = getIntent().getStringExtra("help");
            if(help.equals("search")){
                myWebView.loadUrl("https://antonioj2000.github.io/YOURGARAGE/buscar.html");
            }else if(help.equals("form")){
                myWebView.loadUrl("https://antonioj2000.github.io/YOURGARAGE/formulario.html");
            }else if(help.equals("list")){
                myWebView.loadUrl("https://antonioj2000.github.io/YOURGARAGE/listado.html");
            }else{
                myWebView.loadUrl("https://antonioj2000.github.io/YOURGARAGE/");
            }

    }
}