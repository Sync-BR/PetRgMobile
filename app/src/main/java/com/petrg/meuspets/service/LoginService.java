package com.petrg.meuspets.service;


import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.petrg.meuspets.MainActivity;
import com.petrg.meuspets.model.LoginModel;
import com.petrg.meuspets.util.JsonUtil;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;

import java.io.IOException;

public class LoginService  {
    private static OkHttpClient cliente;
    private static Context context;
    private static Gson gson = new Gson();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private JsonUtil jsonUtil;
    public LoginService(Context Context){
        cliente = new OkHttpClient();
        context = Context;
    }

    public void autenticar(LoginModel login, Context context){
        String url = "http://186.247.89.58:8080/api/login/authenticate";
        String json = gson.toJson(login);
        RequestBody body = RequestBody.create(json,JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        cliente.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ((MainActivity) context).runOnUiThread(() -> {
                    Toast.makeText(context, "Erro na solicitação: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    ((MainActivity) context).runOnUiThread(() -> {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Sucesso ao autenticar " ,Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(context, "Login ou senha incorretar " ,Toast.LENGTH_SHORT).show();
                    });

            }
        });

    }


}
