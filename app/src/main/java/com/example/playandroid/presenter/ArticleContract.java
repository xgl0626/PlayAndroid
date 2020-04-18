package com.example.playandroid.presenter;

import android.view.View;

import okhttp3.Callback;

/**
 * @author 徐国林
 * @data 2020/3/30
 * @decription 主页文章契约
 */
public interface ArticleContract {
    interface ArticlePresenter{
        void getData(String address, Callback callback);
        void clickItems(View view, String uri);
    }
    interface ArticleView {
        void initView();
        void initRefresh();
        void initData(int index);
        void initBanner();

    }

    interface ArticleModel{
      void getData(String address, Callback callback);
    }
}
