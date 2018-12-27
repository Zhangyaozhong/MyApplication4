package com.bwie.android.mvpmodel.view;

import com.bwie.android.mvpmodel.entity.UserEntity;

public interface IregisterView {
    void failure(String msg);
    void success(UserEntity userEntity);
    void mobileError(String s);
}
