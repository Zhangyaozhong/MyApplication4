package com.bwie.android.mvpmodel.model;

import com.bwie.android.mvpmodel.net.RegisterCallback;

import java.util.HashMap;

public interface IRegisterModel {
    void register(HashMap<String,String> params, RegisterCallback requestCallback);
}
