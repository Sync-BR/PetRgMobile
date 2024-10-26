package com.petrg.meuspets.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.petrg.meuspets.callbacks.register.RegisterCallback;
import com.petrg.meuspets.model.UsuarioModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterService {
    private OkHttpClient cliente;
    private Context context;
    private Gson gson;
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public RegisterService(Context context) {
        this.cliente = new OkHttpClient();
        this.context = context;
        this.gson = new Gson();
    }

    public void registerUser(UsuarioModel usuarioModel, RegisterCallback registerCallback){
        String url = "http://186.247.89.58:8080/api/user/create/user";
        String json = gson.toJson(usuarioModel);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        cliente.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                registerCallback.onServerFailure();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    registerCallback.onAuthSuccess();
                } else{
                    registerCallback.onAuthFailure();
                }
            }
        });
    }
}
