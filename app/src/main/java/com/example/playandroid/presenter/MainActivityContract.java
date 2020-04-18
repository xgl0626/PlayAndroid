package com.example.playandroid.presenter;

import android.graphics.Bitmap;

/**
 * @author 徐国林
 * @data 2020/4/5
 * @decription main契约
 */
public interface MainActivityContract {
    interface MainActivityPresenter{
        void loadPhoto(String key, Bitmap bitmap);
    }
    interface MainActivityView{
        void initViews();
        void cancelActionBar();
        void setUpNavigationView();
    }
    interface MainActivityModel{
        void loadPhoto(String key, Bitmap bitmap);
    }
}
