package com.example.playandroid.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 徐国林
 * @data 2020/3/30
 * @decription
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    protected T mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        viewCreated();
    }

    protected void viewCreated(){
        if(mPresenter!=null)
            mPresenter.addView(this);
    }


    @Override
    protected void onDestroy(){
        if(mPresenter!=null)
            mPresenter.destroyView();
        super.onDestroy();
    }

}
