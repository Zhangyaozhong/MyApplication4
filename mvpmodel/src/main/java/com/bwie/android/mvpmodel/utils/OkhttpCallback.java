package com.bwie.android.mvpmodel.utils;

public interface OkhttpCallback {
    void failure(String msg);//服务器请求失败：断网等
    void success(String result);
}
