package com.bwie.android.mvpmodel.presenter;

import com.bwie.android.mvpmodel.entity.UserEntity;
import com.bwie.android.mvpmodel.model.IRegisterModel;
import com.bwie.android.mvpmodel.model.RegisterModel;
import com.bwie.android.mvpmodel.net.RequestCallback;
import com.bwie.android.mvpmodel.utils.ValidatorUtil;
import com.bwie.android.mvpmodel.view.IregisterView;

import java.util.HashMap;

public class RegPresenter {
    private IRegisterModel IRegisterModel;
    private  IregisterView iregisterView;

    public RegPresenter(IregisterView iregisterView) {
       IRegisterModel = new RegisterModel();
        this.iregisterView = iregisterView;
    }

    public void register (final HashMap<String,String> params){
        String mobile ="18612991023";
        String password = "11111111";
        if (!ValidatorUtil.isMobile(mobile)){
            if (iregisterView!=null){
                iregisterView.mobileError("请输入合法手机号");
            }
            return;//返回
        }
        IRegisterModel.login(params, new RequestCallback() {
            @Override
            public void success(UserEntity userEntity) {
                    if (iregisterView!=null){
                        iregisterView.success(userEntity);
                    }
            }

            @Override
            public void error(String msg) {
                if (iregisterView!=null){
                    iregisterView.failure(msg);
                }
            }
        });
    }
}
