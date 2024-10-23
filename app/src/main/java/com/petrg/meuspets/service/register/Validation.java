package com.petrg.meuspets.service.register;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.petrg.meuspets.activity.register.RegisterActivity;
import com.petrg.meuspets.callbacks.ValidationCallback;
import com.petrg.meuspets.callbacks.ValidationCpfCallback;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
}
