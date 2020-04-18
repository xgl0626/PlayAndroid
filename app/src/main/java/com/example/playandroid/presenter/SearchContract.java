package com.example.playandroid.presenter;

import com.example.playandroid.model.bean.TagBean;

import java.util.List;

/**
 * @author 徐国林
 * @data 2020/4/6
 * @decription 搜索页面契约
 */
public interface SearchContract {
    interface SearchPresenter{
        void getData(String address,okhttp3.Callback callback);
    }
    interface SearchView{
        void initView();
        void initTagData();
        void initNetData();
        void setUpActionBar();
    }
    interface SearchModel{
        void postData(String address,String params,okhttp3.Callback callback);
        void getData(String address, okhttp3.Callback callback);
    }
}
