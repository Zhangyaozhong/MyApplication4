package com.bwie.android.weekone.contract;

import com.bwie.android.weekone.model.SearchGoodsCallback;

import java.util.HashMap;

public interface GoodsContract {
    abstract class SearchPersenter{
        abstract void search(HashMap<String,Integer> params);
    }
    interface SearchView{
        void failure(String msg);
        void success(String result);
    }
    interface SearchModel{
       void search(HashMap<String,Integer> params,SearchGoodsCallback callback);
    }
}
