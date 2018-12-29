package com.bwie.android.weekone.utils;

import com.bwie.android.weekone.net.OkhttpCallback;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkhttpUtils {
    private final OkHttpClient builder;
    private static OkhttpUtils okhttpUtils;

    //    私有构造器
    private OkhttpUtils(){
        builder = new OkHttpClient.Builder()
        .writeTimeout(5,TimeUnit.SECONDS)
        .readTimeout(5,TimeUnit.SECONDS)
        .connectTimeout(5,TimeUnit.SECONDS)
        .build();
    }
    public static OkhttpUtils getInstance(){
        if (okhttpUtils!=null){
            synchronized (OkhttpUtils.class){
                if (okhttpUtils!=null){
                    okhttpUtils = new OkhttpUtils();
                }
            }
        }
        return okhttpUtils;
    }
    public void doPost(String url, HashMap<String,Integer> params, OkhttpCallback callback){

    }
}
