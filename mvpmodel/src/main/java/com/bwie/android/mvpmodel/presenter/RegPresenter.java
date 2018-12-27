package com.bwie.android.mvpmodel.presenter;

import com.bwie.android.mvpmodel.entity.UserEntity;
import com.bwie.android.mvpmodel.model.RegisterModel;
import com.bwie.android.mvpmodel.net.RegisterCallback;
import com.bwie.android.mvpmodel.utils.ValidatorUtil;
import com.bwie.android.mvpmodel.view.IregisterView;

import java.util.HashMap;

public class RegPresenter  {
    private RegisterModel registerModel;
    private  IregisterView iregisterView;

    public RegPresenter(IregisterView iregisterView) {
        registerModel = new RegisterModel();
        this.iregisterView = iregisterView;
    }


    public void register(HashMap<String,String> params){
        String mobile = params.get("mobile");
        String password = params.get("password");
        if (!ValidatorUtil.isMobile(mobile)){
            if (iregisterView!=null){
                iregisterView.mobileError("请输入合法手机号");
            }
            return;//返回
        }
        if (registerModel!=null){
            registerModel.register(params, new RegisterCallback() {
                @Override
                public void success(UserEntity userEntity) {

                }

                @Override
                public void onResponse(String result) {
                    if (iregisterView!=null){
                        iregisterView.success(result);
                    }
                }

                @Override
                public void error(String msg) {
                    if (iregisterView!=null){
                        iregisterView.mobileError("手机号不正确");
                    }
                }
            });
        }
    }
}
