package com.example.playandroid.presenter;

import android.content.Intent;
import android.view.View;


import com.example.playandroid.Utils.LogUtil;
import com.example.playandroid.Utils.MyApplication;
import com.example.playandroid.model.ArticleModel;
import com.example.playandroid.view.fragments.Activity.ArticleActivity;
import com.example.playandroid.view.fragments.Activity.MainActivity;
import com.example.playandroid.view.fragments.Activity.SearchActivity;
import com.example.playandroid.view.fragments.MainFragment;


import okhttp3.Callback;

/**
 * @author 徐国林
 * @data 2020/3/30
 * @decription
 */
public class ArticlePresenter implements ArticleContract.ArticlePresenter {
    private ArticleContract.ArticleView articleView;
    private ArticleContract.ArticleModel articleModel;
    public ArticlePresenter() {
        articleView = new MainFragment();
        articleModel = new ArticleModel();
    }

    @Override
    public void getData(String address, Callback callback) {
        articleModel.getData(address, callback);

    }

    @Override
    public void clickItems(View view, String uri) {
        Intent intent = new Intent(view.getContext(), ArticleActivity.class);
        intent.putExtra("uri",uri);
        LogUtil.d(uri);
        view.getContext().startActivity(intent);
    }

}

