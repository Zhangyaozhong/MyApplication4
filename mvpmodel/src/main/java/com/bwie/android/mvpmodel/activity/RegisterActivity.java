package com.bwie.android.mvpmodel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.android.mvpmodel.MainActivity;
import com.bwie.android.mvpmodel.R;
import com.bwie.android.mvpmodel.contract.user.IRegContract;
import com.bwie.android.mvpmodel.presenter.RegPresenter;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements IRegContract.IregisterView {
    private EditText etPhone;
    private EditText etPassword;
    private Button btn;
    private String phone;
    private String pwd;
    private RegPresenter regPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regPresenter = new RegPresenter(this);
        etPhone = findViewById(R.id.editText);
        etPassword = findViewById(R.id.editText3);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> params = new HashMap<>();
                phone = etPhone.getText().toString();
                pwd = etPassword.getText().toString();
                params.put("mobile",phone);
                params.put("password",pwd);
                regPresenter.register(params);
            }
        });
    }

    @Override
    public void mobileError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void success(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }

    @Override
    public void failure(String msg) {

    }
}
