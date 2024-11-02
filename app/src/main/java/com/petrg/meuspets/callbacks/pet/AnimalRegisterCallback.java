package com.petrg.meuspets.callbacks.pet;

public interface AnimalRegisterCallback {
    void onAuthSuccess();
    void onAuthFailure();
    void onServerFailure();
}
