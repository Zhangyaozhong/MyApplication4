package com.bwie.android.zhangyaozhong1229.presenter;

import com.bwie.android.zhangyaozhong1229.contract.PersonalContract;
import com.bwie.android.zhangyaozhong1229.model.PersonalModel;
import com.bwie.android.zhangyaozhong1229.net.IRequestCallback;

import java.util.HashMap;

public class PersonalPresenter extends PersonalContract.PersonalPresenter {
    private PersonalContract.PersonalView personalView;
    private PersonalModel personalModel;
    public PersonalPresenter(PersonalContract.PersonalView personalView) {
        this.personalView = personalView;
        personalModel = new PersonalModel();
    }

    @Override
    public void personal(HashMap<String, String> params) {
        String uid = params.get("uid");
        if (personalModel!=null){
            personalModel.personal(params, new IRequestCallback() {
                @Override
                public void failure(String msg) {
                    if (personalView!=null){
                        personalView.failure("网络异常，请稍后在试");
                    }
                }

                @Override
                public void success(String result) {
                    if (personalView!=null){
                        personalView.success(result);
                    }
                }
            });
        }
    }
}
