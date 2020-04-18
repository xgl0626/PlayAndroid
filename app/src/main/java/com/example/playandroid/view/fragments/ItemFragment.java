package com.example.playandroid.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playandroid.R;
import com.example.playandroid.base.BaseFragment;

public class ItemFragment extends BaseFragment {
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(getLayoutId(),container,false);
        return mView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragment;
    }

}
