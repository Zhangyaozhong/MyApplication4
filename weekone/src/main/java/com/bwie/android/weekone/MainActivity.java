package com.bwie.android.weekone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwie.android.weekone.adapter.SearchAdapter;
import com.bwie.android.weekone.contract.GoodsContract;
import com.bwie.android.weekone.entity.HomeDataEntity;
import com.bwie.android.weekone.presenter.SearchPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements GoodsContract.ISearchView {
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.list_search)
    XRecyclerView list_search;
    private int page = 1;
    private SearchPresenter searchPersenter;
    private SearchAdapter searchAdapter;
    private List<HomeDataEntity.DataBean> list = new ArrayList<>();
    public static int TYPT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {

        list_search.setPullRefreshEnabled(true);

        list_search.setLayoutManager(new LinearLayoutManager(MainActivity.this, 1, false));
        list_search.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                TYPT = 1;
                page = 1;
                load();
                list_search.refreshComplete();


            }

            @Override
            public void onLoadMore() {
                TYPT = 2;
                page++;
                load();
                list_search.loadMoreComplete();
            }
        });
    }

    private void initData() {
        searchPersenter = new SearchPresenter(this);


    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void success(String result) {
        parseResult(result);
    }

    @OnClick(R.id.btn_search)
    public void click(View v) {
        load();
    }

    private void load() {
        HashMap<String, String> params = new HashMap<>();
        if (!et_search.getText().toString().isEmpty()) {
            params.put("keywords", et_search.getText().toString().trim());
            params.put("page", page + "");
            if (searchPersenter != null) {
                searchPersenter.search(params);
            }
        }

    }

    private void parseResult(String result) {

        HomeDataEntity homeDataEntity = new Gson().fromJson(result, HomeDataEntity.class);
        List<HomeDataEntity.DataBean> data = homeDataEntity.getData();
        if (TYPT == 1) {
            list.clear();
        }
        list.addAll(data);
        if (searchAdapter == null) {
            searchAdapter = new SearchAdapter(this, list);
            list_search.setAdapter(searchAdapter);
        } else {

            searchAdapter.notifyDataSetChanged();

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPersenter.destroy();
    }
}
