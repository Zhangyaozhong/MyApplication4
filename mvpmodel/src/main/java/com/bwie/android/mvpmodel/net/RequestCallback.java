package com.bwie.android.mvpmodel.net;

import com.bwie.android.mvpmodel.entity.UserEntity;

public interface RequestCallback  {
     void success(UserEntity userEntity);
     void error(String msg);
}
