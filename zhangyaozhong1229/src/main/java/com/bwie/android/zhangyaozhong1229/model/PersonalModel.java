package com.bwie.android.zhangyaozhong1229.model;

import com.bwie.android.zhangyaozhong1229.api.PersonalApi;
import com.bwie.android.zhangyaozhong1229.contract.PersonalContract;
import com.bwie.android.zhangyaozhong1229.net.IRequestCallback;
import com.bwie.android.zhangyaozhong1229.net.OkhttpCallback;
import com.bwie.android.zhangyaozhong1229.utils.OkhttpUtils;

import java.util.HashMap;

public class PersonalModel implements PersonalContract.PersonalModel {
    @Override
    public void personal(HashMap<String, String> params, final IRequestCallback callback) {
        OkhttpUtils.getIntance().doPost(PersonalApi.PERSONAL, params, new OkhttpCallback() {
            @Override
            public void success(String result) {
                if (callback!=null){
                    callback.success(result);
                }
            }

            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }
        });
    }
}
