package com.bwie.android.zhangyaozhong1229.contract;

import com.bwie.android.zhangyaozhong1229.net.IRequestCallback;

import java.util.HashMap;

public interface PersonalContract {
     abstract class PersonalPresenter{
         public abstract void personal(HashMap<String,String> params);
     }
     interface PersonalModel{
         void personal(HashMap<String,String> params,IRequestCallback callback);
     }
     interface PersonalView{
         void success(String result);
         void phoneError(String msg);
         void failure(String msg);
     }
}
