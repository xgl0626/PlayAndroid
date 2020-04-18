package com.example.playandroid.model;

import com.example.playandroid.Utils.OkhttpUtil;
import com.example.playandroid.presenter.SearchContract;

import okhttp3.Callback;

/**
 * @author 徐国林
 * @data 2020/4/6
 * @decription
 */
public class SearchModel implements SearchContract.SearchModel {
    @Override
    public void postData(String address, String params, Callback callback) {
        OkhttpUtil.post(address,params,callback);
    }

    @Override
    public void getData(String address, okhttp3.Callback callback) {
        OkhttpUtil.get(address, callback);
    }

}
