package com.bwie.android.weekone.contract;

import com.bwie.android.weekone.model.SearchGoodsCallback;

import java.util.HashMap;

public interface GoodsContract {
    abstract class SearchPersenter{
        protected abstract void search(HashMap<String, String> params);
    }
    interface ISearchView{
        void failure(String msg);
        void success(String result);
    }
    interface ISearchModel{
       void search(HashMap<String,String> params,SearchGoodsCallback callback);
    }
}
