package com.petrg.meuspets.activity.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;

import com.petrg.meuspets.R;
import com.petrg.meuspets.activity.pet.RegisterPetActivity;
import com.petrg.meuspets.enums.PetBreedEnums;
import com.petrg.meuspets.implementation.Structure;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity implements Structure {
    private Toolbar toolbar;
    private AppCompatImageButton addNewPet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        initializeUI();
        setupListeners();
    }

    @Override
    public void initializeUI() {
        toolbar = findViewById(R.id.toolbar);
        addNewPet = findViewById(R.id.add_pet_button);

        setSupportActionBar(toolbar);
    }

    @Override
    public void setupListeners() {
        addNewPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPetScreen = new Intent(UserActivity.this, RegisterPetActivity.class);
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


}
