package com.petrg.meuspets.activity.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;

import com.petrg.meuspets.R;
import com.petrg.meuspets.activity.pet.RegisterPetActivity;
import com.petrg.meuspets.callbacks.usuario.PetCallBack;
import com.petrg.meuspets.implementation.Structure;
import com.petrg.meuspets.model.LoginModel;
import com.petrg.meuspets.model.PetModel;
import com.petrg.meuspets.service.register.ValidationService;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActivity extends AppCompatActivity implements Structure {
    private Toolbar toolbar;
    private AppCompatImageButton addNewPet;
    private LoginModel loginModel;
    private ValidationService validarPets = new ValidationService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        initializeUI();
        setupListeners();
        validatePets();
        setSupportActionBar(toolbar);
    }

    @Override
    public void initializeUI() {
        toolbar = findViewById(R.id.toolbar);
        Intent intent = getIntent();
        addNewPet = findViewById(R.id.add_pet_button);
        loginModel = (LoginModel) intent.getSerializableExtra("usuario");
    }

    @Override
    public void setupListeners() {
        addNewPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPetScreen = new Intent(UserActivity.this, RegisterPetActivity.class).putExtra("usuario", loginModel);
                startActivity(addPetScreen);
            }
        });
    }

    @Override
    public void disableButton() {

    }

    @Override
    public void enableButton() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_perfil) {
            Toast.makeText(this, "Abrindo Perfil", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_sair) {
            Toast.makeText(this, "Saindo", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void validatePets() {
        validarPets.getPetUser(loginModel.getUsuarioModel().getId(), new PetCallBack() {
            @Override
            public void onSuccess(List<PetModel>[] pet) {
                List<PetModel> listPets = new ArrayList<>();
                for (List<PetModel> pets : pet) {
                    listPets.addAll(pets);
                }
                addNewPet.setVisibility(View.INVISIBLE);
            }

            @Override
            public void empty() {
                addNewPet.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                Toast.makeText(UserActivity.this, "Erro no sistema!", Toast.LENGTH_LONG).show();
            }
        });
    }


}
