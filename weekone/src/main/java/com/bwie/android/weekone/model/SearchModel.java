package com.bwie.android.weekone.model;

import com.bwie.android.weekone.api.SearchGoodsApi;
import com.bwie.android.weekone.contract.GoodsContract;
import com.bwie.android.weekone.net.OkhttpCallback;
import com.bwie.android.weekone.utils.OkhttpUtils;

import java.util.HashMap;

public class SearchModel implements GoodsContract.ISearchModel {

    @Override
    public void search(HashMap<String, String> params, final SearchGoodsCallback callback) {
        OkhttpUtils.getInstance().doPost(SearchGoodsApi.SEARCH_URL, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback != null) {
                    callback.error(msg);
                }
            }

            @Override
            public void successful(String Result) {
                if (callback != null) {
                    callback.success(Result);
                }
            }
        });
    }
}
