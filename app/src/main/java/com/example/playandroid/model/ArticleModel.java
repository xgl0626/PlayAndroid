package com.example.playandroid.model;


import com.example.playandroid.Utils.OkhttpUtil;
import com.example.playandroid.presenter.ArticleContract;

import okhttp3.Callback;

/**
 * @author 徐国林
 * @data 2020/4/3
 * @decription
 */
public class ArticleModel implements ArticleContract.ArticleModel {

    @Override
    public void getData(String address, Callback callback) {
        OkhttpUtil.get(address, callback);
    }

}
