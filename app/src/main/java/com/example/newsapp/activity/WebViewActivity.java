package com.example.newsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.newsapp.R;
import com.example.newsapp.fragment.CustomSectionFragment;

public class WebViewActivity extends AppCompatActivity {
 WebView webViewSeeMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent i=getIntent();
        String articleUrl=i.getStringExtra(CustomSectionFragment.WEB_VIEW_INTENT);
        webViewSeeMore=findViewById(R.id.web_view_see_more);
        webViewSeeMore.setWebViewClient(new WebViewClient());
        webViewSeeMore.loadUrl(articleUrl);
    }
    @Override
    public void onBackPressed(){
        if(webViewSeeMore.canGoBack()) {
            webViewSeeMore.goBack();
        }else{
            super.onBackPressed();
        }
    }
}