package com.petrg.meuspets.service.register;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.petrg.meuspets.activity.register.RegisterActivity;

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
    //Refatorar todo o codigo
    public void validationEmail(String email) {
        System.out.println("passei" + email);
        String url = "http://186.247.89.58:8080/api/user/check/email/" + email;
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        new Thread(() -> {
            try {
                Response response = cliente.newCall(request).execute();
                if (response.isSuccessful()) {
                    System.out.println("Bem sucedis");
                    String responseBody = response.body().string();
                    if (responseBody.contains("true")) {
                        System.out.println("Endereço de email existente");
                    } else if (responseBody.contains("false")) {
                        System.out.println("Não encontrado esse endereço de email");
                    }
                } else {
                    //Refactor
                    ((RegisterActivity) context).runOnUiThread(() -> {
                        Toast.makeText(context, "Falhou ao verificar cpf", Toast.LENGTH_LONG).show();
                    });
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }).start();

    }
}
