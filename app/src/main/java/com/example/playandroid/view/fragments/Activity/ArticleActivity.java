package com.example.playandroid.view.fragments.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.playandroid.R;
import com.example.playandroid.Utils.LogUtil;
import com.example.playandroid.base.BaseActivity;

/**
 * @author 徐国林
 * @data 2020/4/5
 * @decription
 */
public class ArticleActivity extends BaseActivity {
    private WebView webView;
    private String uri;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artcle_activity);
        webView = findViewById(R.id.article_content);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        Intent intent = getIntent();
        uri = intent.getStringExtra("uri");
        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript
        webSettings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(uri);
        setUpActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }
}
