package com.bwie.android.mvpmodel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.android.mvpmodel.activity.GoodsActivity;
import com.bwie.android.mvpmodel.activity.RegisterActivity;
import com.bwie.android.mvpmodel.contract.user.ILoginContract;
import com.bwie.android.mvpmodel.presenter.ILoginPresenter;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ILoginContract.ILoginView, View.OnClickListener {

    private EditText etPhone;
    private EditText etPassword;
    private String phone;
    private String password;
    private ILoginContract.LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

        loginPresenter = new ILoginPresenter(this);
    }

    private void initView() {
        etPhone = findViewById(R.id.editText1);
        etPassword = findViewById(R.id.editText2);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                startActivityForResult(new Intent(MainActivity.this, RegisterActivity.class), 2);
                finish();
                break;
            case R.id.button1:
                HashMap<String, String> params = new HashMap<>();
                phone = etPhone.getText().toString();
                password = etPassword.getText().toString();
                params.put("mobile", phone);
                params.put("password", password);
                if (loginPresenter != null) {
                    loginPresenter.login(params);
                }

        }
    }

    @Override
    public void success(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, GoodsActivity.class);
        startActivity(intent);
    }

    @Override
    public void phoneError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
