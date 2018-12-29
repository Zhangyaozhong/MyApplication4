package com.bwie.android.mvpmodel.model;

import android.os.Handler;

import com.bwie.android.mvpmodel.api.UserApi;
import com.bwie.android.mvpmodel.contract.user.IRegContract;
import com.bwie.android.mvpmodel.net.RegisterCallback;
import com.bwie.android.mvpmodel.utils.OkhttpCallback;
import com.bwie.android.mvpmodel.utils.OkhttpUtils;

import java.util.HashMap;

public class RegisterModel implements IRegContract.IRegisterModel {
    Handler handler = new Handler();


    @Override
    public void register(HashMap<String, String> params, final RegisterCallback registerCallback) {
        OkhttpUtils.getInstance().doPost(UserApi.USER_REGISTER, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                registerCallback.error("网络异常，请稍后再试");
               /* handler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerCallback.error("网络异常，请稍后再试");

                    }
                });*/
            }

            @Override
            public void success(final String result) {
                if (registerCallback != null) {
                    registerCallback.onResponse(result);
                 /*   handler.post(new Runnable() {
                        @Override
                        public void run() {
                            registerCallback.onResponse(result);
                        }
                    });*/
                }
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
