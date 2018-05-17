package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {
    private WebView web_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web_view = findViewById(R.id.web_view);

        Intent intent = getIntent();
        String detailUrl = intent.getStringExtra("detailUrl");
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        web_view.loadUrl(detailUrl);
    }
}
