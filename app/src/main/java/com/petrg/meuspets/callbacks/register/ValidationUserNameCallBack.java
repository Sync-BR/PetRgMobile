package com.petrg.meuspets.callbacks.register;

public interface ValidationUserNameCallBack {
    void onAuthSuccess();
    void onAuthFailure();
    void onServerFailure();
}
