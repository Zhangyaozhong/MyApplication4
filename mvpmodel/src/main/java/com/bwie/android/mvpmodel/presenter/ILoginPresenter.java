package com.bwie.android.mvpmodel.presenter;

import com.bwie.android.mvpmodel.contract.user.ILoginContract;
import com.bwie.android.mvpmodel.entity.UserEntity;
import com.bwie.android.mvpmodel.model.LoginModel;
import com.bwie.android.mvpmodel.net.RegisterCallback;
import com.bwie.android.mvpmodel.utils.ValidatorUtil;

import java.util.HashMap;

public class ILoginPresenter extends ILoginContract.LoginPresenter {
    private ILoginContract.ILoginView iLoginView;
    private LoginModel loginModel;

    public ILoginPresenter(ILoginContract.ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginModel();
    }

    @Override
    public void login(HashMap<String, String> params) {
        //正则表达式校验
        String mobile = params.get("mobile");
        String password = params.get("password");
        if (!ValidatorUtil.isMobile(mobile)){
            if (iLoginView!=null){
                iLoginView.failure("您输入的手机号有误，请重新输入");
            }
            return;
        }
        //调用loginmodel的数据处理的方法，登录的方法
        if (loginModel!=null){
            loginModel.login(params, new RegisterCallback() {
                @Override
                public void success(UserEntity userEntity) {
                    if (iLoginView!=null){
                        iLoginView.success(userEntity.getMsg());
                    }
                }

                @Override
                public void successMsg(String msg) {
                    if (iLoginView!=null){
                        iLoginView.successMsg(msg);
                    }
                }

                @Override
                public void onResponse(String result) {

                }

                @Override
                public void error(String msg) {
                    if (iLoginView!=null){
                        iLoginView.failure(msg);
                    }
                }
            });
        }
    }
}
