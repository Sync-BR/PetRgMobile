package com.petrg.meuspets.callbacks.usuario;

import com.petrg.meuspets.model.PetModel;

import java.util.List;

public interface PetCallBack {
    void onSuccess(List<PetModel>[] pet);
    void empty();
    void onError();

}
