package com.petrg.meuspets.activity.user;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.petrg.meuspets.R;
import com.petrg.meuspets.activity.pet.RegisterPetActivity;
import com.petrg.meuspets.adapter.PetAdapter;
import com.petrg.meuspets.callbacks.usuario.PetCallBack;
import com.petrg.meuspets.implementation.Structure;
import com.petrg.meuspets.model.LoginModel;
<<<<<<< HEAD
<<<<<<< HEAD

=======
import com.petrg.meuspets.model.PetModel;
import com.petrg.meuspets.service.register.ValidationService;
>>>>>>> refactor
=======
import com.petrg.meuspets.model.PetModel;
import com.petrg.meuspets.service.register.ValidationService;
>>>>>>> refactor
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements Structure {
    private Toolbar toolbar;
    private RecyclerView petList;
    private AppCompatImageButton addNewPet;
    private LoginModel loginModel;
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private ValidationService validarPets;
    private PetAdapter petAdapter;
    private List<PetModel> listPet;
>>>>>>> refactor
=======
    private ValidationService validarPets;
    private PetAdapter petAdapter;
    private List<PetModel> listPet;
>>>>>>> refactor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        initializeUI();
        setupListeners();
        validatePets();
        setSupportActionBar(toolbar);
        listPet = new ArrayList<>();
        petAdapter = new PetAdapter(listPet);
        petList.setLayoutManager(new LinearLayoutManager(this));
        petList.setAdapter(petAdapter);
    }

    @Override
    public void initializeUI() {
        toolbar = findViewById(R.id.toolbar);
        petList = findViewById(R.id.recyclerViewPets);
        addNewPet = findViewById(R.id.add_pet_button);
        Intent intent = getIntent();
        loginModel = (LoginModel) intent.getSerializableExtra("usuario");
<<<<<<< HEAD
<<<<<<< HEAD
        System.out.println("tela user: " +loginModel);
        setSupportActionBar(toolbar);
=======
        validarPets = new ValidationService(this);
>>>>>>> refactor
=======
        validarPets = new ValidationService(this);
>>>>>>> refactor
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
            //User profile screen
            Intent screenUserProfile = new Intent(this, UserPerfilActivity.class);
            screenUserProfile.putExtra("usuario", loginModel);
            startActivity(screenUserProfile);
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
                for (List<PetModel> pets : pet) {
                    listPet.addAll(pets);
                }
                addNewPet.setVisibility(View.INVISIBLE);
                petList.setVisibility(View.VISIBLE);
            }

            @Override
            public void empty() {
                addNewPet.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams params = petList.getLayoutParams();
                int heightInPixels = (int) (300 * getResources().getDisplayMetrics().density);
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                petList.setLayoutParams(params);
                petList.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                Toast.makeText(UserActivity.this, "Erro no sistema!", Toast.LENGTH_LONG).show();
            }
        });
    }


}
