package com.petrg.meuspets.activity.pet;

import static com.petrg.meuspets.service.upload.UploadImage.pickImageRequest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.activity.user.UserActivity;
import com.petrg.meuspets.callbacks.pet.AnimalRegisterCallback;
import com.petrg.meuspets.implementation.Structure;
import com.petrg.meuspets.model.LoginModel;
import com.petrg.meuspets.model.PetModel;
import com.petrg.meuspets.model.UploadModel;
import com.petrg.meuspets.service.register.RegisterPetService;
import com.petrg.meuspets.service.upload.UploadImage;

public class CompletePetRegistration extends AppCompatActivity implements Structure {
    private LoginModel loginModel;
    private PetModel petModel;
    private UploadImage uploadImage;
    private ImageView Img_selected;
    private Button selectimg, register;
    private RegisterPetService servicePet;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet_setup2);
        initializeUI();
        setupListeners();
        Intent intent = getIntent();
        servicePet = new RegisterPetService();
        loginModel = (LoginModel) intent.getSerializableExtra("usuario");
        petModel = (PetModel) intent.getSerializableExtra("pet");
        uploadImage = new UploadImage(this);
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, pickImageRequest);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pickImageRequest && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            Img_selected.setImageURI(imageUri);
            Img_selected.setTag(imageUri.toString()); // Armazenar o URI no tag da ImageView
        }
    }
    @Override
    public void initializeUI() {
        uploadImage = new UploadImage(CompletePetRegistration.this);
        selectimg = findViewById(R.id.select_imagem);
        Img_selected = findViewById(R.id.image_selected);
        register = findViewById(R.id.register_pet_ok);
    }

    @Override
    public void setupListeners() {
        selectimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uploadImage != null) {
                    uploadImage.openGallery();
                } else {
                    Toast.makeText(CompletePetRegistration.this, "Erro ao carregar a imagem.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Img_selected.getDrawable() != null) {
                    try {
                        Uri imageUri = Uri.parse(Img_selected.getTag().toString());
                        String fileName = uploadImage.uploadImage(imageUri);
                        PetModel petUrl = new PetModel(fileName);
                        petModel.setPhoto(petUrl.getPhoto());

                        servicePet.registerPet(petModel, new AnimalRegisterCallback() {
                            @Override
                            public void onAuthSuccess() {
                                Intent homeScreen = new Intent(CompletePetRegistration.this, UserActivity.class );
                                homeScreen.putExtra("usuario", loginModel);
                                homeScreen.putExtra("pet", petModel);
                                startActivity(homeScreen);
                            }

                            @Override
                            public void onAuthFailure() {

                            }

                            @Override
                            public void onServerFailure() {

                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("erro");
                }
            }
        });
    }

    @Override
    public void disableButton() {

    }

    @Override
    public void enableButton() {

    }
}
