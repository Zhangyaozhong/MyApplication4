package com.bwie.android.mvpmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bwie.android.mvpmodel.entity.UserEntity;
import com.bwie.android.mvpmodel.presenter.RegPresenter;
import com.bwie.android.mvpmodel.view.IregisterView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements IregisterView,View.OnClickListener {

    private RegPresenter regPresenter;
    private EditText etPhone;
    private EditText etPassword;
    private String phone;
    private String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        etPhone = findViewById(R.id.editText1);
        etPassword = findViewById(R.id.editText2);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    private void initData() {
        regPresenter = new RegPresenter(this);

    }
    /**
     * 点击，登录
     * @param
     */
    public void login() {

        HashMap<String,String> params = new HashMap<>();
        phone = etPhone.getText().toString();
        pwd = etPassword.getText().toString();
        params.put("mobile",phone);
        params.put("password",pwd);

        if (regPresenter !=null){
            regPresenter.register(params);
        }

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void success(UserEntity userEntity) {

    }

    @Override
    public void mobileError(String s) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                login();
        }
    }
}
