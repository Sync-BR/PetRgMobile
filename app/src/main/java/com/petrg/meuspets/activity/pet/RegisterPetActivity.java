package com.petrg.meuspets.activity.pet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.enums.CatBreedEnums;
import com.petrg.meuspets.enums.DogBreedEnums;
import com.petrg.meuspets.enums.TypeAnimals;
import com.petrg.meuspets.implementation.Structure;
import com.petrg.meuspets.service.upload.UploadImage;

public class RegisterPetActivity extends AppCompatActivity implements Structure {
    private Spinner petBreed, typeAnimal;
    private EditText name, age, weightPet, observation, photo;
    private Button selectimg;
    private ImageView Img_selected;
    private int breedValue = 0;
    private UploadImage uploadImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);
        initializeUI();
        setupListeners();
        animalAdapter();
    }

    @Override
    public void initializeUI() {
        petBreed = findViewById(R.id.breed_spinner);
        typeAnimal = findViewById(R.id.type_animal_spinner);
        name = findViewById(R.id.name_pet);
        age = findViewById(R.id.age_pet);
        weightPet = findViewById(R.id.weigth_pet);
        observation = findViewById(R.id.observation_pet);
        selectimg = findViewById(R.id.select_imagem);
        Img_selected = findViewById(R.id.image_selected);
        uploadImage = new UploadImage(this);

    }

    @Override
    public void setupListeners() {
        typeAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String animalType = (String) adapterView.getItemAtPosition(i);
                TypeAnimals selectedAnimal = getAnimalType(animalType);
                if (animalType.equals(TypeAnimals.dog.getAnimal())) {
                    dogType();
                } else if (animalType.equals(TypeAnimals.cat.getAnimal())) {
                    catType();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        petBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (getBreedValue() == 0) {
                    getDogBreed((String) adapterView.getItemAtPosition(i));
                } else if (getBreedValue() == 1) {
                    getCatBreed((String) adapterView.getItemAtPosition(i));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        selectimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage.openGallery();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UploadImage.pickImageRequest && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            Img_selected.setImageURI(imageUri);
        }
    }

    @Override
    public void disableButton() {

    }

    @Override
    public void enableButton() {

    }

    private void animalAdapter() {
        ArrayAdapter<CharSequence> adapterAnimalType = ArrayAdapter.createFromResource(this, R.array.type_animal_spinner, android.R.layout.simple_spinner_item);
        adapterAnimalType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAnimal.setAdapter(adapterAnimalType);

    }

    private void dogType() {
        this.breedValue = 0;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pet_breeds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petBreed.setAdapter(adapter);
    }

    private void catType() {
        this.breedValue = 1;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.racas_gatos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petBreed.setAdapter(adapter);
    }

    private TypeAnimals getAnimalType(String animalType) {
        TypeAnimals selectedAnimal = null;
        for (TypeAnimals type : TypeAnimals.values()) {
            if (type.getAnimal().equals(animalType)) {

                return type;
            }

        }
        return selectedAnimal;
    }

    //Get dog breed
    private void getDogBreed(String raceSelected) {
        DogBreedEnums dogBreed = null;
        for (DogBreedEnums dogs : DogBreedEnums.values()) {
            if (dogs.getRace().equals(raceSelected)) {
                dogBreed = dogs;
                break;
            }
        }
        Toast.makeText(RegisterPetActivity.this, "Raça: " + raceSelected + ", Value: " + dogBreed.getValue(), Toast.LENGTH_LONG).show();
    }

    private void getCatBreed(String raceSelected) {
        CatBreedEnums catBreedE = null;
        for (CatBreedEnums cats : CatBreedEnums.values()) {
            if (cats.getRace().equals(raceSelected)) {
                catBreedE = cats;
                break;
            }
        }
        Toast.makeText(RegisterPetActivity.this, "Raça: " + raceSelected + ", Value: " + catBreedE.getValue(), Toast.LENGTH_LONG).show();
    }

    public int getBreedValue() {
        return breedValue;
    }
}
