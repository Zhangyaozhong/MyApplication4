package com.bwie.android.mvpmodel.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AppIntercpptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //得到开发者创建的request对象
        Request request = chain.request();
//得到原来的response对象
        Response response = chain.proceed(request);
        Log.d("result:", response.body().string());
        return response;
    }
}
