package com.bwie.android.zhangyaozhong1227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bwie.android.zhangyaozhong1227.adapter.MyAdapter;
import com.bwie.android.zhangyaozhong1227.entity.GoodsEntity;
import com.bwie.android.zhangyaozhong1227.utils.MyAsyncTask;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity {
    private int page = 1;
    private XListView xlv;
    private List<GoodsEntity.DataBean> list = new ArrayList<>();
    public static  int TYPE = 1;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initView() {
        xlv = findViewById(R.id.xlv);
        xlv.setPullRefreshEnable(true);
        xlv.setPullLoadEnable(true);
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                TYPE = 1;
               page = 1;
                initData();
                xlv.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                TYPE =2;
                page++;
                initData();
                xlv.stopLoadMore();
            }
        });
    }

    private void initData() {

        String urlPath = "http://120.27.23.105/product/getProducts?pscid=39&page=" + page;
        MyAsyncTask myAsyncTask = new MyAsyncTask(new MyAsyncTask.Icallback() {
            @Override
            public void getData(String s) {
                Gson gson = new Gson();
                GoodsEntity goodsEntity = gson.fromJson(s, GoodsEntity.class);
                Log.i("TAG", "getData: "+s);

                List<GoodsEntity.DataBean> data = goodsEntity.getData();
                if (TYPE==1){
                    list.clear();
                }
                list.addAll(data);
                setAdapter();
            }
        });
        myAsyncTask.execute(urlPath);
    }

    private void setAdapter() {
        if (myAdapter==null){
            myAdapter = new MyAdapter(MainActivity.this, list);
            xlv.setAdapter(myAdapter);
        }else {
            myAdapter.notifyDataSetChanged();
        }

    }

}
