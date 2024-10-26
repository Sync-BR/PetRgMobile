package com.petrg.meuspets.activity.pet;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.enums.PetBreedEnums;
import com.petrg.meuspets.enums.TypeAnimals;
import com.petrg.meuspets.implementation.Structure;

public class RegisterPetActivity extends AppCompatActivity implements Structure {
    private Spinner petBreed, typeAnimal;

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

    }

    @Override
    public void setupListeners() {
        typeAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String animalType = (String) adapterView.getItemAtPosition(i);
                //Criar uma função para executar esse código.
                TypeAnimals selectedAnimal = null;
                for (TypeAnimals type : TypeAnimals.values()) {
                    if (type.getAnimal().equals(animalType)) {
                        selectedAnimal = type;
                        break;
                    }
                }
                if (selectedAnimal != null) {
                    int value = selectedAnimal.getValue();
                    Toast.makeText(RegisterPetActivity.this, "Animal selecionado: " + animalType + " | Value: " + value, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterPetActivity.this, "Animal não encontrado", Toast.LENGTH_LONG).show();
                }
                if(animalType.equals(TypeAnimals.dog.getAnimal())){
                    animalType();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        petBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String racaSelected = (String) adapterView.getItemAtPosition(i);
                int value = PetBreedEnums.valueOf(racaSelected.replace(" ", "_").toUpperCase()).getValue();
                Toast.makeText(RegisterPetActivity.this, "Raça: " + racaSelected + ", Value: " + value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void disableButton() {

    }

    @Override
    public void enableButton() {

    }

    private void animalAdapter() {
        //Adapter animal
        ArrayAdapter<CharSequence> adapterAnimalType = ArrayAdapter.createFromResource(this, R.array.type_animal_spinner, android.R.layout.simple_spinner_item);
        adapterAnimalType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAnimal.setAdapter(adapterAnimalType);

    }
    private void animalType(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pet_breeds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petBreed.setAdapter(adapter);
    }
}
