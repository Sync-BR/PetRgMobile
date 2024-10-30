package com.petrg.meuspets.callbacks.usuario;

import com.petrg.meuspets.model.LoginModel;

public interface ReceiveUserdataCallBack {
    void onSuccess(LoginModel loginModel);
    void onError(Exception e);
}
