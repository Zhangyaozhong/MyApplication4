package com.bwie.android.zhangyaozhong1229;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.android.zhangyaozhong1229.contract.PersonalContract;
import com.bwie.android.zhangyaozhong1229.pojo.UserPojo;
import com.bwie.android.zhangyaozhong1229.presenter.PersonalPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PersonalContract.PersonalView {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.nick_name)
    TextView nick_name;
    private PersonalContract.PersonalPresenter personalPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       personalPresenter = new PersonalPresenter(this);
        HashMap<String,String> params = new HashMap<>();
        params.put("uid",100+"");

       personalPresenter.personal(params);
    }


    @Override
    public void success(String result) {
//        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        UserPojo userPojo = gson.fromJson(result, UserPojo.class);
        UserPojo.DataBean data = userPojo.getData();
        String icon = data.getIcon();
        Glide.with(MainActivity.this)
                .load(icon)
                .into(imageView);
        user_name.setText(data.getUsername());
        nick_name.setText(data.getNickname());

    }

    @Override
    public void phoneError(String msg) {

    }

    @Override
    public void failure(String msg) {

    }
}
