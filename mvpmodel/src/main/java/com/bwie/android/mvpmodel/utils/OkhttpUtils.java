package com.bwie.android.mvpmodel.utils;

import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * okhttp二次封装，封装网络框架
 */
public class OkhttpUtils {
    private Handler handler = new Handler();
    //    私有属性
    private static OkhttpUtils okhttpUtils;
    private final OkHttpClient okHttpClient;

    //私有构造方法
    private OkhttpUtils() {
//        链式调用，构建者模式
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)//添加日志拦截器
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 创建事例，双重检验锁，单例模式进行实例化
     */
    public static OkhttpUtils getInstance() {
        if (okhttpUtils == null) {
            synchronized (OkhttpUtils.class) {
                okhttpUtils = new OkhttpUtils();
            }
        }
        return okhttpUtils;
    }

    /**
     * get请求方式
     */
    public void doGet(String url, final OkhttpCallback okhttpCallback) {
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.failure("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (okhttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.success(result);
                        }
                    });
                }
            }
        });
    }

    /**
     * post请求方式
     */
    public void doPost(String url, HashMap<String, String> parmas, final OkhttpCallback okhttpCallback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : parmas.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.failure("网络异常=======");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (okhttpCallback != null) {
                    if (200 == response.code()) {
                        final String result = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallback.success(result);
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * 上传头像，文件
     */
    public void uplodeFile(String url, HashMap<String, Object> params, final OkhttpCallback callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);//多表单不止包含原生表单也包含文件multipart/form-data
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof File) {
                File file = (File) value;
                builder.addFormDataPart(key, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
            } else {
                builder.addFormDataPart(key, value.toString());
            }
        }
        Request build = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.failure("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (callback != null) {
                    final String result = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.success(result);
                        }
                    });
                }
            }
        });


    }

    /**
     * 取消所有请求
     * 好处：节省开销，内存开销，cpu开销（线程的开销）
     */
    public void cancelAllTask() {
        if (okHttpClient != null) {
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
