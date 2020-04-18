package com.example.playandroid.view.fragments.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.widget.Toolbar;

import com.example.playandroid.R;
import com.example.playandroid.Utils.LogUtil;
import com.example.playandroid.base.BaseActivity;
import com.example.playandroid.model.bean.ArticleBean;
import com.example.playandroid.model.bean.NetBean;
import com.example.playandroid.model.bean.TagBean;
import com.example.playandroid.presenter.ArticleContract;
import com.example.playandroid.presenter.ArticlePresenter;
import com.example.playandroid.presenter.SearchContract;
import com.example.playandroid.presenter.SearchPresenter;
import com.example.playandroid.view.fragments.Adapter.ArticleListAdapter;
import com.example.playandroid.view.fragments.Adapter.TagAdapter;
import com.example.playandroid.view.fragments.Widget.FlowTagLayout;
import com.example.playandroid.view.fragments.Widget.OnTagClickListener;
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
 * @data 2020/4/5
 * @decription
 */
public class SearchActivity extends BaseActivity implements SearchContract.SearchView, View.OnClickListener {
    private Toolbar toolbar;
    private EditText et_search;
    private ImageView tv_search;
    private ImageView iv_delete;
    private TagAdapter mTagAdapter;
    private TagAdapter netAdapter;
    private List<TagBean> tagData;
    List<String> flowData;
    List<String> netData;
    private FlowTagLayout flowLayout;
    private FlowTagLayout net_flowLayout;
    private List<NetBean> netBeans;
    private final int FIRST_REFRESH = 0;
    private final int FLOW = 1;
    final String NET_LISTS = "https://www.wanandroid.com/friend/json";
    final String SEARCH_LISTS = "https://www.wanandroid.com//hotkey/json";
    final String[] name = {"#990033", "#CC6699", "#99FFFF", "#66FF66", "#99CC00", "#FF00FF", "#FFCCFF", "#663399", "#FF0000"};

    private SearchContract.SearchPresenter presenter;
    private ArticleContract.ArticlePresenter articlePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        setUpActionBar();
        presenter = new SearchPresenter();
        articlePresenter=new ArticlePresenter();
        initView();
        initTagData();
        initNetData();
    }

    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        et_search = findViewById(R.id.et_search);
        net_flowLayout=findViewById(R.id.net_flowLayout);
        netAdapter = new com.example.playandroid.view.fragments.Adapter.TagAdapter<>(this);
        net_flowLayout.setAdapter(netAdapter);
        net_flowLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
               articlePresenter.clickItems(view,netBeans.get(position).getLink());
            }
        });
        flowLayout = findViewById(R.id.flowLayout);
        mTagAdapter = new com.example.playandroid.view.fragments.Adapter.TagAdapter<>(this);
        flowLayout.setAdapter(mTagAdapter);
        flowLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                et_search.setText(tagData.get(position).getName());
            }
        });
        iv_delete = findViewById(R.id.iv_delete);
        tv_search=findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
        et_search.setOnClickListener(this);
        iv_delete.setOnClickListener(this);
    }

    @Override
    public void initTagData() {
        tagData = new ArrayList<>();
        presenter.getData(SEARCH_LISTS, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    LogUtil.d(jsonData);
                    String data = getElement(jsonData, "data");
                    Gson gson = new Gson();
                    tagData = gson.fromJson(data, new TypeToken<List<TagBean>>() {
                    }.getType());
                    flowData = new ArrayList<>();
                    for (int i = 0; i < tagData.size(); i++) {
                        flowData.add((tagData.get(i).getName()));
                    }
                    sendMsg(FLOW);
                }
            }
        });
    }

    @Override
    public void initNetData() {
        netData = new ArrayList<>();
        presenter.getData(NET_LISTS, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    LogUtil.d(jsonData);
                    String data = getElement(jsonData, "data");
                    Gson gson = new Gson();
                    netBeans = gson.fromJson(data, new TypeToken<List<NetBean>>() {
                    }.getType());
                    for (int i = 0; i < netBeans.size(); i++) {
                        netData.add((netBeans.get(i).getName()));
                    }
                    sendMsg(FIRST_REFRESH);
                }
            }
        });
    }

    public void sendMsg(int index) {
        Message message = new Message();
        message.what = index;
        handler.sendMessage(message);
    }

    //handler处理ui更新，有没有其他办法感觉，这样写很勉强。
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                netAdapter.clearAndAddAll(netData);
            }
            if (msg.what == 1) {
                mTagAdapter.clearAndAddAll(flowData);
            }
        }
    };

    @Override
    public void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_delete:et_search.setText("");
            break;
            case R.id.tv_search:onSearch(et_search.getText().toString());

        }
    }
    public void onSearch(String name)
    {
        if(et_search.getText().toString().equals(name)&&!et_search.getText().equals("")) {
            Intent intent = new Intent(this, AuthorArticleActivity.class);
            intent.putExtra("params", name);
            startActivity(intent);
        }
    }
}
