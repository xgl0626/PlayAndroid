package com.example.playandroid.view.fragments.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.R;
import com.example.playandroid.base.BaseActivity;

/**
 * @author 徐国林
 * @data 2020/4/9
 * @decription
 */
public class QuestionActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private EditText editText;
    private TextView textView;
    private ImageView imageView;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        setUpActionBar();
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_question);
        editText = findViewById(R.id.et_msg);
        textView = findViewById(R.id.tv_send);
        imageView = findViewById(R.id.iv_img);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }

    public void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
