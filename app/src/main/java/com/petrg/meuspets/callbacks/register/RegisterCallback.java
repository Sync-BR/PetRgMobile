package com.petrg.meuspets.callbacks.register;

public interface RegisterCallback {
    void onAuthSuccess();
    void onAuthFailure();
    void onServerFailure();
}
