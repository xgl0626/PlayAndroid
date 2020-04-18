package com.example.playandroid.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.playandroid.R;
import com.example.playandroid.Utils.LogUtil;
import com.example.playandroid.Utils.RecycleViewScrollListener;
import com.example.playandroid.Utils.ToastUtils;
import com.example.playandroid.base.BaseFragment;
import com.example.playandroid.model.bean.ArticleBean;
import com.example.playandroid.model.bean.BannerBean;
import com.example.playandroid.presenter.ArticleContract;
import com.example.playandroid.presenter.ArticlePresenter;
import com.example.playandroid.view.fragments.Adapter.ArticleListAdapter;
import com.example.playandroid.view.fragments.Adapter.BannAdapter;
import com.example.playandroid.view.fragments.Widget.banner.Banner;
import com.example.playandroid.view.fragments.Widget.banner.BannerPagerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.playandroid.Utils.Json.getElement;

/*
实现上拉加载更多，下来刷新，文章列表，banner。
 */
public class MainFragment extends BaseFragment implements ArticleContract.ArticleView {
    private View mView;
    private ArticleContract.ArticlePresenter articlePresenter;
    private SwipeRefreshLayout refreshLayout;
    private ArticleListAdapter articleListAdapter;
    private RecyclerView recyclerView;
    private List<ArticleBean> articleBeanList;
    private List<BannerBean> bannerBeans;
    private boolean Loading;
    private Banner banner;
    private BannAdapter adapter;
    private static final int FIRST_REFRESH = 0;
    private static final int LOAD_REFRESH = 1;
    private static final int BANNER = 2;
    protected int page = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        articlePresenter = new ArticlePresenter();
        initView();
        initRefresh();
        initBanner();
        //多个fragment切换出现adapter消失，百度后需再onCreateview中在设置一遍应该是fragment生命周期问题
        recyclerView = mView.findViewById(R.id.recycler_view);
        refreshLayout = mView.findViewById(R.id.refreshLayout);
        //设置刷新球颜色
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#BBFFFF"));
        //手动调用,通知系统去测量
        refreshLayout.setRefreshing(true);
        initData(FIRST_REFRESH);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        initData(FIRST_REFRESH);
                    }
                }).start();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                initData(LOAD_REFRESH);
            }
        });
        articleListAdapter = new ArticleListAdapter(this.getActivity());
        recyclerView.setAdapter(articleListAdapter);
        return mView;
    }

    @Override
    public void initBanner() {
        banner = mView.findViewById(R.id.banner);
        adapter = new BannAdapter(this.getActivity());
        bannerBeans=new ArrayList<>();
        String BANNER_LISTS = "https://www.wanandroid.com/banner/json";
        articlePresenter.getData(BANNER_LISTS, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bannerData = response.body().string();
                LogUtil.d(bannerData);
                String data = getElement(bannerData, "data");
                LogUtil.d(data);
                Gson gson = new Gson();
                bannerBeans = gson.fromJson(data, new TypeToken<List<BannerBean>>() {
                }.getType());
                sendMsg(BANNER);
            }
        });
    }

    @Override
    public void initRefresh() {
        //设置刷新球颜色
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#BBFFFF"));
        //手动调用,通知系统去测量
//        mSwipeRefreshLayout.measure(0,0);
        refreshLayout.setRefreshing(true);
        initData(FIRST_REFRESH);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        initData(FIRST_REFRESH);
                    }
                }).start();
            }
        });
    }

    public void sendMsg(int index) {
        Message message = new Message();
        message.what = index;
        handler.sendMessage(message);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    public void initView() {
        if (Loading)
            return;
        Loading = true;
        recyclerView = mView.findViewById(R.id.recycler_view);
        refreshLayout = mView.findViewById(R.id.refreshLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                initData(LOAD_REFRESH);
            }
        });
        articleListAdapter = new ArticleListAdapter(this.getActivity());
        recyclerView.setAdapter(articleListAdapter);
    }

    //handler处理ui更新，有没有其他办法感觉，这样写很勉强。
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                articleListAdapter.refreshArticleList((ArrayList<ArticleBean>) articleBeanList);
            }
            if (msg.what == 1)
                articleListAdapter.addArticle((ArrayList<ArticleBean>) articleBeanList);
            if(msg.what==2) {
                adapter.setData(bannerBeans);
                banner.setDot(R.drawable.no_selected_dot, R.drawable.selected_dot);
                banner.setDotGravity(Gravity.BOTTOM);
                banner.setAdapter(adapter);
                banner.setOnItemClickListener(new BannerPagerAdapter.onItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        articlePresenter.clickItems(banner,bannerBeans.get(position).getUrl());
                    }
                }).startAutoPlay();
            }
        }
    };
    //article数据请求
    @Override
    public void initData(final int index) {
        LogUtil.d("start");
        String ARTICLE_LISTS = "https://www.wanandroid.com/article/list/" + page + "/json";
        articlePresenter.getData(ARTICLE_LISTS, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtils.showHint(e.toString());
                refreshLayout.setRefreshing(false);
                Loading = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String jsonData = response.body().string();
                    String data = getElement(jsonData, "data");
                    String datas = getElement(data, "datas");
                    Gson gson = new Gson();
                    articleBeanList = gson.fromJson(datas, new TypeToken<List<ArticleBean>>() {
                    }.getType());
                    if (index == FIRST_REFRESH)
                        sendMsg(FIRST_REFRESH);
                    else sendMsg(LOAD_REFRESH);
                    if (refreshLayout.isRefreshing())
                        refreshLayout.setRefreshing(false);
                } else
                    ToastUtils.showError(response.body().string());
            }
        });
        page++;
    }
}
