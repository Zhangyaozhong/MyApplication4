package com.bwie.android.mvpmodel.net;

import com.bwie.android.mvpmodel.entity.UserEntity;

public interface RegisterCallback {
    void success(UserEntity userEntity);
    void onResponse(String result);
    void error(String msg);
}
