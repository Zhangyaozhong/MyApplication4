package com.bwie.android.mvpmodel.model;

import com.bwie.android.mvpmodel.net.RequestCallback;

import java.util.HashMap;

public interface IRegisterModel {
    void login(HashMap<String,String> params, RequestCallback requestCallback);
}
