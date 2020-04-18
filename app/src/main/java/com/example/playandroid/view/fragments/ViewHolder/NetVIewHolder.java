package com.example.playandroid.view.fragments.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.R;
import com.example.playandroid.model.bean.NetBean;
import com.example.playandroid.presenter.ArticleContract;
import com.example.playandroid.presenter.ArticlePresenter;
import com.example.playandroid.presenter.SearchContract;
import com.example.playandroid.presenter.SearchPresenter;
import com.example.playandroid.view.fragments.Activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐国林
 * @data 2020/4/6
 * @decription
 */
public class NetVIewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private List<NetBean> netBeans;
    private ArticleContract.ArticlePresenter presenter;

    public NetVIewHolder(@NonNull View itemView, ArrayList<NetBean> arrayList) {
        super(itemView);
        presenter = new ArticlePresenter();
        this.netBeans = arrayList;
        textView = itemView.findViewById(R.id.item_tag);
    }

    public void upData(NetBean bean) {
        textView.setText(bean.getName());
        //复用article_activity
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickItems(v, bean.getLink());
            }
        });
    }
}
