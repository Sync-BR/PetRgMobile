package com.petrg.meuspets.service.register;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.petrg.meuspets.activity.register.RegisterActivity;
import com.petrg.meuspets.activity.register.RegisterLoginActivity;
import com.petrg.meuspets.callbacks.register.ValidationCallback;
import com.petrg.meuspets.callbacks.register.ValidationCpfCallback;
import com.petrg.meuspets.callbacks.register.ValidationUserNameCallBack;
import com.petrg.meuspets.callbacks.usuario.PetCallBack;
import com.petrg.meuspets.model.PetModel;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Validation {
    private Context context;

    public Validation(Context context) {
        this.context = context;
    }

    public List<PetModel> convertJsonToPetList(String json) {
        Gson gson = new Gson();
        Type petListType = new TypeToken<List<PetModel>>() {
        }.getType();
        return gson.fromJson(json, petListType);
    }

    public void getPetUser(int idUser, PetCallBack petCallBack) {
        final String url = "http://186.247.89.58:8080/api/pets/consultar/" + idUser;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                petCallBack.onError();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    System.out.println(jsonResponse);
                    Gson gson = new Gson();
                    final List<PetModel> listPet = convertJsonToPetList(jsonResponse);
                    if (!listPet.isEmpty()) {
                        petCallBack.onSuccess(new List[]{listPet});
                    } else {
                        petCallBack.empty();
                    }
                } else {
                    petCallBack.onError();
                }
            }
        });

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
