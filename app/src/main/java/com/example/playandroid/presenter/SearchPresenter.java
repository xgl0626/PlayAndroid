package com.example.playandroid.presenter;


import com.example.playandroid.model.SearchModel;

import okhttp3.Callback;

/**
 * @author 徐国林
 * @data 2020/4/6
 * @decription
 */
public class SearchPresenter implements SearchContract.SearchPresenter {
    private SearchContract.SearchModel model;
    public SearchPresenter(){
        model=new SearchModel();
    }
    @Override
    public void getData(String address, okhttp3.Callback callback) {
        model.getData(address,callback);
    }
}
