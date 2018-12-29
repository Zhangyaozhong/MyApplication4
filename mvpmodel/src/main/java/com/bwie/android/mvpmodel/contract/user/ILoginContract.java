package com.bwie.android.mvpmodel.contract.user;

import com.bwie.android.mvpmodel.net.RegisterCallback;

import java.util.HashMap;

public interface ILoginContract {
    abstract class LoginPresenter {
        public abstract void login(HashMap<String, String> params);
    }

    interface ILoginView {
        void success(String result);
        void phoneError(String msg);
        void failure(String msg);
        void successMsg(String msg);
    }

    ;

    interface ILoginModel {
        void login(HashMap<String, String> params, RegisterCallback callback);
    }
}
