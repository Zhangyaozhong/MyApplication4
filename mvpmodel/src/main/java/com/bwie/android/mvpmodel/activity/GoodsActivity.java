package com.bwie.android.mvpmodel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bwie.android.mvpmodel.GoodsAdapter;
import com.bwie.android.mvpmodel.R;
import com.bwie.android.mvpmodel.api.GoodsApi;
import com.bwie.android.mvpmodel.entity.GoodsPojo;
import com.bwie.android.mvpmodel.utils.OkhttpCallback;
import com.bwie.android.mvpmodel.utils.OkhttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsActivity extends AppCompatActivity {
    @BindView(R.id.rv_goods)
    RecyclerView recyclerView;
    private List<GoodsPojo.Product> list;
    int page = 1;
    private GoodsAdapter goodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        initView();
        initData();
    }

    private void initData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("keywords", "手机");
        params.put("page", page + "");
        OkhttpUtils.getInstance().doPost(GoodsApi.GOODS_URL, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {

            }

            @Override
            public void success(String result) {
                parseResult(result);
            }
        });


    }

    private void parseResult(String result) {
        Gson gson = new Gson();
        GoodsPojo goodsPojo = gson.fromJson(result, GoodsPojo.class);
        if (goodsPojo!=null){
            goodsAdapter = new GoodsAdapter(GoodsActivity.this, goodsPojo.data);
            recyclerView.setAdapter(goodsAdapter);
        }
        goodsAdapter.setClickListener(new GoodsAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(GoodsActivity.this, "onItemClick"+position, Toast.LENGTH_SHORT).show();
            }

           @Override
            public void onItemLongClick(int position, View view) {
               Toast.makeText(GoodsActivity.this, "onItemLongClick"+position, Toast.LENGTH_SHORT).show();
            }

           /*  @Override
            public void onItemClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                Toast.makeText(GoodsActivity.this, "onItemClick"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                Toast.makeText(GoodsActivity.this, "onItemLongClick"+position, Toast.LENGTH_SHORT).show();

            }*/
        });
    }

    private void initView() {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,1,false));
    }
}
