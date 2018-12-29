package com.bwie.android.mvpmodel.contract.user;

import com.bwie.android.mvpmodel.net.RegisterCallback;

import java.util.HashMap;

/**
 * 注册契约类接口，统一管理
 */
public interface IRegContract {
    /**
     * P层的抽象方法
     */
     abstract class RegPresenter {
        public abstract void register(HashMap<String, String> params);
    }

    /**
     * M层的回调接口
     */
    interface IRegisterModel {
        void register(HashMap<String, String> params, RegisterCallback requestCallback);
    }

    /**
     * v层的回调接口
     */
    interface IregisterView {
        void mobileError(String error);

        void success(String result);

        void failure(String msg);
    }
}
