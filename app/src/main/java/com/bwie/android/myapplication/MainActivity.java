package com.bwie.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.android.myapplication.bean.LoginBean;
import com.bwie.android.myapplication.utils.MyAsyncTask;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etPassword;
    private String urlPath;
    private String phone;
    private String pwd;
    private LoginBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone = findViewById(R.id.editText1);
        etPassword = findViewById(R.id.editText2);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                LoginBean.DataBean dataBean = initData();
               // String mobile = dataBean.getMobile();
               // String password = dataBean.getPassword();
                if (true) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ShowActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }

        }
    }

    private LoginBean.DataBean initData() {
        phone = etPhone.getText().toString();
        pwd = etPassword.getText().toString();
        urlPath = "http://120.27.23.105/user/login?mobile=" + phone + "&password=" + pwd;
        MyAsyncTask myAsyncTask = new MyAsyncTask(new MyAsyncTask.IcallBack() {
            @Override
            public void getData(String dataString) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(dataString, LoginBean.class);
                String msg = loginBean.getMsg();
                data = loginBean.getData();
                Log.i("TAG", "getData: ==============" + data);
            }
        });
        myAsyncTask.execute(urlPath);
        return data;
    }
}
