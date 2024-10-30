package com.petrg.meuspets.service.register;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.petrg.meuspets.callbacks.pet.AnimalRegisterCallback;
import com.petrg.meuspets.model.PetModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterPetService {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public void registerPet(PetModel pet, AnimalRegisterCallback animalRegisterCallback){
        System.out.println("Dados " +pet);
        final String url = "http://186.247.89.58:8080/api/pets/creater";
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String json = gson.toJson(pet);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    // A requisição foi bem-sucedida
                    System.out.println("Pet registrado com sucesso: " + response.body().string());
                } else {
                    // Ocorreu um erro na requisição
                    System.out.println("Erro ao registrar pet: " + response.message());
                }
            }
        });
    }

}
