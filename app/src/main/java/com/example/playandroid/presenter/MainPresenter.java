package com.example.playandroid.presenter;

import android.graphics.Bitmap;

import com.example.playandroid.model.MainActivityModel;
import com.example.playandroid.view.fragments.Activity.MainActivity;


/**
 * @author 徐国林
 * @data 2020/4/5
 * @decription
 */
public class MainPresenter implements MainActivityContract.MainActivityPresenter {
    private MainActivityContract.MainActivityView mainActivityView;
    private MainActivityContract.MainActivityModel mainActivityModel;
    public MainPresenter() {
        mainActivityModel = new MainActivityModel();
        mainActivityView = new MainActivity();
    }

    @Override
    public void loadPhoto(String key, Bitmap bitmap) {

    }
}
