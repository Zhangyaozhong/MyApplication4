package com.bwie.android.mvpmodel.model;

import android.os.Handler;
import android.text.TextUtils;

import com.bwie.android.mvpmodel.api.UserApi;
import com.bwie.android.mvpmodel.contract.user.ILoginContract;
import com.bwie.android.mvpmodel.entity.UserEntity;
import com.bwie.android.mvpmodel.net.RegisterCallback;
import com.bwie.android.mvpmodel.utils.OkhttpCallback;
import com.bwie.android.mvpmodel.utils.OkhttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginModel implements ILoginContract.ILoginModel {
    Handler handler = new Handler();

    @Override
    public void login(HashMap<String, String> params, final RegisterCallback callback) {
        OkhttpUtils.getInstance().doPost(UserApi.USER_LOGIN, params, new OkhttpCallback() {
            @Override
            public void failure(final String msg) {
                if (callback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.error(msg);
                        }
                    });
                }
            }

            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)) {
                    Gson gson = new Gson();
                    final UserEntity userEntity = gson.fromJson(result, UserEntity.class);
                    if (callback != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.success(userEntity);
                            }
                        });
                    }
                }
            }
        });
    }
}
