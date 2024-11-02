package com.petrg.meuspets.callbacks.register;

public interface ValidationCpfCallback {
    void onAuthSuccess();
    void onAuthFailure();
    void onServerFailure();
}
