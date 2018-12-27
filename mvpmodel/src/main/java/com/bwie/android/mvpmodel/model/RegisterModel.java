package com.bwie.android.mvpmodel.model;

import android.os.Handler;

import com.bwie.android.mvpmodel.api.UserApi;
import com.bwie.android.mvpmodel.net.RegisterCallback;

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
    public void register(HashMap<String, String> params, final RegisterCallback registerCallback) {
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
        final Request request = new Request.Builder().url(UserApi.USER_REGISTER).post(formBody.build()).build();
        //创建请求执行对象
        Call call = client.newCall(request);
//        异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (registerCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            registerCallback.error("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (registerCallback!=null){
                    final String result = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            registerCallback.onResponse(result);
                        }
                    });
                };
            }
        });
    }

    /**
     * 解析数据变成对象
     * @param result
     * @param RegisterCallback
     *
     */
   /* private void parseResult(String result, final RegisterCallback RegisterCallback) {
        final UserEntity userEntity = new Gson().fromJson(result, UserEntity.class);
        if (RegisterCallback!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    RegisterCallback.success(userEntity);
                }
            });
        }
    }*/
}
