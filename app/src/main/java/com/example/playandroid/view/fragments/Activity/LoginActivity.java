package com.example.playandroid.view.fragments.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playandroid.R;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private Button lg_rigister;
    private CheckBox checkBox;
    private EditText username;
    private EditText password;
    private String Username;
    private String Password;
    private boolean remPass;
    private SharedPreferences remember;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        login = (Button) findViewById(R.id.bt_login);
        username = (EditText) findViewById(R.id.lg_user);
        password = (EditText) findViewById(R.id.lg_psw);
        lg_rigister = (Button) findViewById(R.id.bt_lg_rigister);
        checkBox = (CheckBox) findViewById(R.id.is_remember);
        ActionBar actionBar = getSupportActionBar();     //取消标题头actionbar
        if (actionBar != null) {
            actionBar.hide();
        }
        sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = remember.edit();
                if (checkBox.isChecked()) {
                    editor.putBoolean("ischecked", true);
                    editor.putString("user", username.getText().toString());
                    editor.putString("pass", password.getText().toString());
                    editor.commit();
                } else {
                    editor.clear();
                    editor.putBoolean("ischecked", false);
                    editor.commit();
                }
                initlogin();
            }
        });
        lg_rigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_zhuce = new Intent(LoginActivity.this, RigisterActiivity.class);
                startActivity(intent_zhuce);
            }
        });
        remember = getSharedPreferences("ischecked", MODE_PRIVATE);//（文件名，模式）
        remPass = remember.getBoolean("ischecked", false);
        if (remPass) {
            String u = remember.getString("user", "");
            String p = remember.getString("pass", "");
            username.setText(u);
            password.setText(p);
            checkBox.setChecked(true);
        }
    }

    private void initlogin() {
        Username = username.getText().toString();
        Password = password.getText().toString();
        String saveusrname = sharedPreferences.getString("username", "");
        String savepassword = sharedPreferences.getString("password", "");
        if (Username.equals(saveusrname) && Password.equals(savepassword)) {
            actionStart();
        } else if (Username.equals("") || Password.equals("")) {
            Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "用户名或者密码输入错误", Toast.LENGTH_SHORT).show();
        }
    }

    public void actionStart() {
        Intent intent_login = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent_login);
        finish();
    }
}
