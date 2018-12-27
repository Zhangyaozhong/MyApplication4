package com.bwie.android.mvpmodel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bwie.android.mvpmodel.activity.RegisterActivity;
import com.bwie.android.mvpmodel.entity.UserEntity;
import com.bwie.android.mvpmodel.presenter.RegPresenter;
import com.bwie.android.mvpmodel.view.IregisterView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        etPhone = findViewById(R.id.editText1);
        etPassword = findViewById(R.id.editText2);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }












    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                startActivityForResult(new Intent(MainActivity.this,RegisterActivity.class),2);
                finish();

        }
    }
}
