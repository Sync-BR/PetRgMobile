package com.petrg.meuspets.service.register;

import android.content.Context;

import com.petrg.meuspets.activity.register.RegisterActivity;
import com.petrg.meuspets.activity.register.RegisterLoginActivity;
import com.petrg.meuspets.callbacks.register.ValidationCallback;
import com.petrg.meuspets.callbacks.register.ValidationCpfCallback;
import com.petrg.meuspets.callbacks.register.ValidationUserNameCallBack;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Validation {
    private Context context;

    public Validation(Context context) {
        this.context = context;
    }

    public void validationEmail(String email, ValidationCallback validation) {
        String url = "http://186.247.89.58:8080/api/user/check/email/" + email;
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        new Thread(() -> {
            try {
                Response response = cliente.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    if (responseBody.contains("true")) {
                        ((RegisterActivity) context).runOnUiThread(validation::onAuthFailure);
                    } else if (responseBody.contains("false")) {
                        ((RegisterActivity) context).runOnUiThread(validation::onAuthSuccess);
                    }
                } else {
                    ((RegisterActivity) context).runOnUiThread(validation::onServerFailure);
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }).start();
    }
    public void validationCpf(String cpf, ValidationCpfCallback validationCPF) {
        String url = "http://186.247.89.58:8080/api/user/check/cpf/" + cpf;
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        new Thread(() -> {
            try {
                Response response = cliente.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    if (responseBody.contains("true")) {
                        ((RegisterActivity) context).runOnUiThread(validationCPF::onAuthFailure);
                    } else if (responseBody.contains("false")) {
                        ((RegisterActivity) context).runOnUiThread(validationCPF::onAuthSuccess);
                    }
                } else {
                    ((RegisterActivity) context).runOnUiThread(validationCPF::onServerFailure);
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }).start();
    }
    public void validationUsername(String username, ValidationUserNameCallBack validationCallback) {
        String url = "http://186.247.89.58:8080/api/login/check/username/" + username;
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        new Thread(() -> {
            try {
                Response response = cliente.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    if (responseBody.contains("true")) {
                        ((RegisterLoginActivity) context).runOnUiThread(validationCallback::onAuthFailure);
                    } else if (responseBody.contains("false")) {
                        ((RegisterLoginActivity) context).runOnUiThread(validationCallback::onAuthSuccess);
                    }
                } else {
                    ((RegisterLoginActivity) context).runOnUiThread(validationCallback::onServerFailure);
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }).start();
    }
}
