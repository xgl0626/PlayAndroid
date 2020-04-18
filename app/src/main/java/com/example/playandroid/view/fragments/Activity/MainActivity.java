package com.example.playandroid.view.fragments.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.playandroid.R;
import com.example.playandroid.Utils.BitmapUtils;
import com.example.playandroid.Utils.ToastUtils;
import com.example.playandroid.base.BaseActivity;
import com.example.playandroid.presenter.MainActivityContract;
import com.example.playandroid.presenter.MainPresenter;
import com.example.playandroid.view.fragments.Adapter.FragmentsAdapter;
import com.example.playandroid.view.fragments.ItemFragment;
import com.example.playandroid.view.fragments.MainFragment;
import com.example.playandroid.view.fragments.SystemFragment;
import com.example.playandroid.view.fragments.Widget.CircleImageView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import static com.example.playandroid.Utils.BitmapUtils.REQUEST_CODE_ALBUM;
import static com.example.playandroid.Utils.BitmapUtils.REQUEST_CODE_CAMERA;

/*
MainActivity类中用viewpager+fragments实现四个fragment。
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, MainActivityContract.MainActivityView {
    private static final String TAG = "MainActivity";
    private BottomNavigationView bnView;
    private ViewPager viewpager;
    private Toolbar toolbar;
    private FragmentsAdapter adapter;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private CircleImageView mAvatar;
    private MainActivityContract.MainActivityPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();
        initViews();
        cancelActionBar();
        setUpNavigationView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ALBUM://相册存储权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    BitmapUtils.openAlbum(this);
                } else {
                    ToastUtils.showHint("选择图库需要同意权限");
                }
                break;
            case REQUEST_CODE_CAMERA://相机拍照权限
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许
                    BitmapUtils.openCamera(MainActivity.this);
                } else {//拒绝
                    ToastUtils.showHint("只有同意相机权限,才能使用打开相机");
                }
                break;
            default:
        }
    }

    @Override
    public void cancelActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_user);
        }
    }

    @Override
    public void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bnView = findViewById(R.id.bottom_nav_view);
        viewpager = findViewById(R.id.viewpager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new ItemFragment());
        fragments.add(new SystemFragment());
        adapter = new FragmentsAdapter(fragments, getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        //BottomNavigationView 点击事件监听
        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                // 跳转指定页面：Fragment
                switch (menuId) {
                    case R.id.tab_one:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.tab_two:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.tab_three:
                        viewpager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });
        viewpager.addOnPageChangeListener(this);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    @Override
    public void setUpNavigationView() {
        //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationView.setCheckedItem(R.id.myfravorite);
                switch (item.getItemId()) {
                    case R.id.myfravorite:
                        break;
                    case R.id.question:
                        ActivityStart(QuestionActivity.class);
                        break;
                    case R.id.left:
                        end();
                        break;
                    default:
                }
                return true;
            }
        });
        //personheader
        View view = navigationView.inflateHeaderView(R.layout.nav_header);
        mAvatar = view.findViewById(R.id.icon_img);
        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapUtils.choicePhoto(MainActivity.this);
            }
        });

    }

    //相机、相册、剪切 返回
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case BitmapUtils.TAKE_PHOTO://相机返回
                    Glide.with(MainActivity.this)
                            .load(BitmapUtils.imageUri)
                            .into(mAvatar);
                    break;
                case BitmapUtils.CHOOSE_PHOTO://相册返回
                    try {
                        if (data != null) {
                            //相册返回
                            Uri uri = data.getData();
                            Log.e("返回相册", uri.toString());

                            Glide.with(MainActivity.this)
                                    .load(uri)
                                    .into(mAvatar);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.showHint("图片选择失败");
                    }
                    break;
            }
        } else {
            ToastUtils.showHint("图片选择失败");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.item_search:
                ActivityStart(SearchActivity.class);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        bnView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void ActivityStart(Class<? extends Activity> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

    public void end() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
