package com.example.playandroid.view.fragments.ViewHolder;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.R;
import com.example.playandroid.Utils.LogUtil;
import com.example.playandroid.model.bean.ArticleBean;
import com.example.playandroid.presenter.ArticleContract;
import com.example.playandroid.presenter.ArticlePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐国林
 * @data 2020/3/30
 * @decription
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView authorName;
    private TextView time;
    private TextView articleTitle;
    private TextView superChapterName;
    private ImageButton favoriteButton;
    private List<ArticleBean>articleBeanList;
    private ArticleContract.ArticlePresenter articlePresenter;
    public ArticleViewHolder(@NonNull View itemView, ArrayList<ArticleBean>articleBeans) {
        super(itemView);
        setItemViewOnClickListener(itemView);
        articlePresenter=new ArticlePresenter();
        authorName=itemView.findViewById(R.id.tv_article_author);
        articleTitle=itemView.findViewById(R.id.tv_article_title);
        favoriteButton=itemView.findViewById(R.id.ib_collect);
        time=itemView.findViewById(R.id.tv_article_time);
        superChapterName=itemView.findViewById(R.id.tv_sc_name);
        articleBeanList=articleBeans;
    }

    private void setItemViewOnClickListener(View itemView) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(articleBeanList.get(getLayoutPosition()).getLink());
                articlePresenter.clickItems(v,articleBeanList.get(getLayoutPosition()).getLink());
            }
        });
    }

    public void upData(ArticleBean articleBean)
    {
        authorName.setText(articleBean.getAuthor());
        articleTitle.setText(articleBean.getTitle());
        superChapterName.setText(articleBean.getSuperChapterName());
        time.setText(articleBean.getNiceDate());
        favoriteButton.setBackgroundResource(articleBean.isCollect()? R.drawable.like_filled: R.drawable.like);
    }
    public void addOnListener()
    {
        favoriteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
