package com.example.playandroid.view.fragments.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.R;
import com.example.playandroid.Utils.LogUtil;
import com.example.playandroid.Utils.RecycleViewScrollListener;
import com.example.playandroid.base.BaseActivity;
import com.example.playandroid.model.bean.ArticleBean;
import com.example.playandroid.presenter.ArticleContract;
import com.example.playandroid.presenter.ArticlePresenter;
import com.example.playandroid.view.fragments.Adapter.ArticleListAdapter;
import com.example.playandroid.view.fragments.Widget.banner.BannerPagerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.playandroid.Utils.Json.getElement;

/**
 * @author 徐国林
 * @data 2020/4/12
 * @decription
 */
public class AuthorArticleActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private String params;
    private ArticleListAdapter articleListAdapter;
    private ArticleContract.ArticlePresenter presenter;
    List<ArticleBean> articleBeans;
    private static final int FIRST_REFRESH = 0;
    private static final int LOAD_REFRESH = 1;
    private Toolbar toolbar;
    protected int page1 = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_acticity);
        setUpActionBar();
        Intent intent = getIntent();
        params = intent.getStringExtra("params");
        LogUtil.d(params);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        presenter = new ArticlePresenter();
        recyclerView = findViewById(R.id.recycler_author);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                initData(params, LOAD_REFRESH);
            }
        });
        articleListAdapter = new ArticleListAdapter(this);
        recyclerView.setAdapter(articleListAdapter);
        initData(params, FIRST_REFRESH);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }

    public void initData(String uri, int index) {
        articleBeans = new ArrayList<>();
        String AUTHOR = "https://wanandroid.com/article/list/" + page1 + "/json?author=" + uri;
        presenter.getData(AUTHOR, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                String data = getElement(jsonData, "data");
                String datas = getElement(data, "datas");
                Gson gson = new Gson();
                articleBeans = gson.fromJson(datas, new TypeToken<List<ArticleBean>>() {
                }.getType());
                if (index == FIRST_REFRESH)
                    sendMsg(FIRST_REFRESH);
                else sendMsg(LOAD_REFRESH);
            }
        });
        page1++;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                articleListAdapter.refreshArticleList((ArrayList<ArticleBean>) articleBeans);
            }
            if (msg.what == 1)
                articleListAdapter.addArticle((ArrayList<ArticleBean>) articleBeans);
        }
    };

    public void sendMsg(int index) {
        Message message = new Message();
        message.what = index;
        handler.sendMessage(message);
    }

    public void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
