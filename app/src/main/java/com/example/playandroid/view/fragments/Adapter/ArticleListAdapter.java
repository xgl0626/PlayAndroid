package com.example.playandroid.view.fragments.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.R;
import com.example.playandroid.Utils.LogUtil;
import com.example.playandroid.model.bean.ArticleBean;
import com.example.playandroid.view.fragments.ViewHolder.ArticleViewHolder;
import com.example.playandroid.view.fragments.ViewHolder.FootViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.example.playandroid.R.layout.artcle_item;

/**
 * @author 徐国林
 * @data 2020/3/30
 * @decription
 */
public class ArticleListAdapter extends RecyclerView.Adapter {
    List<ArticleBean>articleBeanList;
    private Context context;
    private final int UPDATA=1;
    private final int ARTICLE=0;
    public ArticleListAdapter(Context context)
    {
        this.context=context;
        articleBeanList=new ArrayList<>();
    }
    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? UPDATA : ARTICLE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ARTICLE:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                ArticleViewHolder articleViewHolder = new ArticleViewHolder(inflater.inflate(artcle_item, parent, false), (ArrayList<ArticleBean>) articleBeanList);
                articleViewHolder.addOnListener();
                return articleViewHolder;
            case UPDATA:
                //底部“加载更多”item
                LayoutInflater inflater2 = LayoutInflater.from(parent.getContext());
                FootViewHolder footerHolder = new FootViewHolder(inflater2.inflate(R.layout.refrsh_item, parent, false));
                return footerHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ARTICLE:
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
            articleViewHolder.upData(articleBeanList.get(position));
            break;
            case UPDATA:
             FootViewHolder footViewHolder =(FootViewHolder)holder;
             break;
        }
    }

    public void refreshArticleList(ArrayList<ArticleBean> newArticleList) {
        articleBeanList.clear();
        addArticle(newArticleList);
    }

    public void addArticle(ArrayList<ArticleBean> articleList) {
        articleBeanList.addAll(articleList);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return articleBeanList.size()+1;
    }
}
