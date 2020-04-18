package com.example.playandroid.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * @author 徐国林
 * @data 2020/3/30
 * @decription
 */
public abstract class BaseFragment <T extends BasePresenter> extends Fragment {
    protected T mPresenter;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         mView = inflater.inflate(getLayoutId(), container,false);
        viewCreated();
        return mView;
    }
    protected void viewCreated(){
        if(mPresenter!=null)
            mPresenter.addView(this);
    }
    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.destroyView();
        super.onDestroyView();
    }
    protected abstract int getLayoutId();
}
