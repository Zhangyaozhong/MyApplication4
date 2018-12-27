package com.bwie.android.mvpmodel.model;

import android.os.Handler;

import com.bwie.android.mvpmodel.api.UserApi;
import com.bwie.android.mvpmodel.entity.UserEntity;
import com.bwie.android.mvpmodel.net.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterModel implements IRegisterModel {
    Handler handler = new Handler();
    @Override
    public void login(HashMap<String, String> params, final RequestCallback requestCallback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
//        对请求体构建参数的过程
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()
                ) {
            formBody.add(p.getKey(),p.getValue());

        }
        Request request = new Request.Builder().url(UserApi.USER_REGISTER).post(formBody.build()).build();
        //创建请求执行对象
        Call call = client.newCall(request);
//        异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback!=null){

                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                int code = response.code();
                parseResult(result,requestCallback,code);
            }
        });
    }

    /**
     * 解析数据变成对象
     * @param result
     * @param requestCallback
     * @param code
     */
    private void parseResult(String result, final RequestCallback requestCallback, int code) {
        final UserEntity userEntity = new Gson().fromJson(result, UserEntity.class);
        if (requestCallback!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    requestCallback.success(userEntity);
                }
            });
        }
    }
}
