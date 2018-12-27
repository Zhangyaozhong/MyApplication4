package com.bwie.android.mvpmodel.view;

public interface IregisterView {
    void mobileError(String error);
    void success(String result);
    void failure(String msg);
}
