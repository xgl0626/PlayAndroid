package com.example.playandroid.view.fragments.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.playandroid.R;
import com.example.playandroid.Utils.LogUtil;
import com.example.playandroid.model.bean.ArticleBean;
import com.example.playandroid.model.bean.BannerBean;
import com.example.playandroid.presenter.ArticleContract;
import com.example.playandroid.presenter.ArticlePresenter;
import com.example.playandroid.view.fragments.Widget.banner.Banner;
import com.example.playandroid.view.fragments.Widget.banner.BannerPagerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author 徐国林
 * @data 2020/4/9
 * @decription
 */
public class BannAdapter extends BannerPagerAdapter {
    private Context mContext;
    private List<BannerBean>data;
    public BannAdapter(Context context) {
        mContext = context;
    }
    @Override
    public void setData(List data) {
        super.setData(data);
        this.data = data;
    }


    @Override
    public View setView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.banner_img, null);
        ImageView iv_bannerImg = v.findViewById(R.id.banner_img);
        TextView tv_title = v.findViewById(R.id.title);
        Glide.with(v).load(data.get(position).getImagePath()).into(iv_bannerImg);
        tv_title.setText(data.get(position).getTitle());
        return v;
    }
}
