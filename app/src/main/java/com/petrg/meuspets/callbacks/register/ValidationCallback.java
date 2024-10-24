package com.petrg.meuspets.callbacks.register;

public interface ValidationCallback {
    void onAuthSuccess();
    void onAuthFailure();
    void onServerFailure();
}

