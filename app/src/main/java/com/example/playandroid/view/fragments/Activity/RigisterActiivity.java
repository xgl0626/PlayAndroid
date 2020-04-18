package com.example.playandroid.view.fragments.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playandroid.R;

public class RigisterActiivity extends AppCompatActivity {
    private Button rigister;
    private EditText username;
    private EditText password;
    private EditText password2;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rigister_activity);
        rigister = (Button) findViewById(R.id.bt_rigister);
        username = (EditText) findViewById(R.id.rg_name);
        password = (EditText) findViewById(R.id.rg_word1);
        password2 = (EditText) findViewById(R.id.rg_word2);
        ActionBar actionBar = getSupportActionBar();     //取消标题头actionbar
        if (actionBar != null) {
            actionBar.hide();
        }
        sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        rigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initrigister();
            }
        });
    }

    private void initrigister() {
        String user = username.getText().toString();
        String psw = password.getText().toString();
        String psw2 = password2.getText().toString();
        if (user.equals("") || psw.equals("") || psw2.equals("")) {
            Toast.makeText(RigisterActiivity.this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (!psw.equals(psw2)) {
            Toast.makeText(RigisterActiivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            rigister(user, psw);
        }
    }

    private void rigister(String user, String psw) {
        final ProgressDialog progressDialog = new ProgressDialog(RigisterActiivity.this);
        progressDialog.setMessage("登录中...");
        progressDialog.show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", user);
        editor.putString("password", psw);
        editor.commit();
        progressDialog.dismiss();
        actionStart();
    }

    private void actionStart() {
        Intent intent = new Intent(RigisterActiivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
