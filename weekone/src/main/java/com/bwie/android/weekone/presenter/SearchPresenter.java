package com.bwie.android.weekone.presenter;

import com.bwie.android.weekone.contract.GoodsContract;
import com.bwie.android.weekone.model.SearchGoodsCallback;
import com.bwie.android.weekone.model.SearchModel;

import java.util.HashMap;

public class SearchPresenter extends GoodsContract.SearchPersenter {
    private GoodsContract.ISearchView searchView;
    private SearchModel searchModel;

    public SearchPresenter(GoodsContract.ISearchView searchView) {
        this.searchView = searchView;
        searchModel = new SearchModel();
    }

    @Override
    public void search(HashMap<String, String> params) {
        if (searchModel != null) {
            searchModel.search(params, new SearchGoodsCallback() {
                @Override
                public void success(String result) {
                    if (searchView != null) {
                        searchView.success(result);
                    }
                }

                @Override
                public void error(String msg) {
                    if (searchView != null) {
                        searchView.failure(msg);
                    }
                }
            });
        }
    }

    /**
     * 销毁方法，解决内存泄漏,解绑
     */
    public void destroy() {

        if (searchView != null) {
            searchView = null;
        }

    }

}
