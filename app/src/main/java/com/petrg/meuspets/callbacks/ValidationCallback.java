package com.petrg.meuspets.callbacks;

public interface ValidationCallback {
    void onAuthSuccess();
    void onAuthFailure();
    void onServerFailure();
}

