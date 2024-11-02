package com.petrg.meuspets.activity.pet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.activity.register.RegisterActivity;
import com.petrg.meuspets.enums.CatBreedEnums;
import com.petrg.meuspets.enums.DogBreedEnums;
import com.petrg.meuspets.enums.TypeAnimals;
import com.petrg.meuspets.implementation.Structure;
import com.petrg.meuspets.model.LoginModel;
import com.petrg.meuspets.model.PetModel;
import com.petrg.meuspets.model.UploadModel;
import com.petrg.meuspets.service.upload.UploadImage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterPetActivity extends AppCompatActivity implements Structure {
    private Spinner petBreed, typeAnimal;
    private EditText name, age, weightPet, observation, textDate;
    private String photo;
    private CheckBox castrated;
    private Button selectimg, buttonNext, buttonReturn;
    private ImageView Img_selected;
    private int breedValue = 0;
    private int idUser;
    private UploadImage uploadImage;
    private Calendar myCalendar;
    private LoginModel loginModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);
        initializeUI();
        setupListeners();
        animalAdapter();
        getCalendar();
    }

    @Override
    public void initializeUI() {
        petBreed = findViewById(R.id.breed_spinner);
        typeAnimal = findViewById(R.id.type_animal_spinner);
        name = findViewById(R.id.name_pet);
        age = findViewById(R.id.age_pet);
        weightPet = findViewById(R.id.weigth_pet);
        observation = findViewById(R.id.observation_pet);
        castrated = findViewById(R.id.check_castrated);
        textDate = findViewById(R.id.text_data_castrated);
        buttonNext = findViewById(R.id.button_register_pet);
        buttonReturn = findViewById(R.id.button_register_pet_return);
        textDate.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        loginModel = (LoginModel) intent.getSerializableExtra("usuario");
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

        castrated.setOnCheckedChangeListener((buttonView, isChecked) -> {
            verificationCastrated();
        });

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAllFields();
                    try {
                        int id_usuario = loginModel.getUsuarioModel().getId();
                        String namePet = name.getText().toString();
                        int agePet = Integer.parseInt(age.getText().toString());
                        TypeAnimals typeAnimalEnum = getAnimalType(typeAnimal.getSelectedItem().toString());
                        Double weight = Double.parseDouble(weightPet.getText().toString());
                        String observationText = observation.getText().toString();
                        System.out.println("Value: " +typeAnimalEnum.getValue());
                        if(typeAnimalEnum.getAnimal() == TypeAnimals.cat.getAnimal()){
                            CatBreedEnums catBreedEnums = getCatBreed(petBreed.getSelectedItem().toString());
                            PetModel petModelCat = new PetModel(id_usuario, namePet, agePet, catBreedEnums, typeAnimalEnum, weight, observationText);
                            Intent registrationPetScree = new Intent(RegisterPetActivity.this, CompletePetRegistration.class);
                            registrationPetScree.putExtra("usuario", loginModel);
                            registrationPetScree.putExtra("pet", petModelCat);
                            startActivity(registrationPetScree);

                        } else {
                            DogBreedEnums race = getDogBreed(petBreed.getSelectedItem().toString());
                            System.out.println("Raça selecionada: " +race);
                            System.out.println("Animal selecionado " +typeAnimalEnum);
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> refactor
=======
>>>>>>> refactor
                            PetModel petModelDog = new PetModel(id_usuario, namePet, agePet, race, typeAnimalEnum, weight, observationText);
                            Intent registrationPetScree = new Intent(RegisterPetActivity.this, CompletePetRegistration.class);
                            registrationPetScree.putExtra("usuario", loginModel);
                            registrationPetScree.putExtra("pet", petModelDog);
                            startActivity(registrationPetScree);


                        }


                    } catch (NumberFormatException e) {
                        Toast.makeText(RegisterPetActivity.this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    }


    @Override
    public void disableButton() {
        buttonNext.setEnabled(false);
    }

    @Override
    public void enableButton() {
        buttonNext.setEnabled(true);
    }

    private boolean verificationCastrated() {
        if (castrated.isChecked()) {
            textDate.setVisibility(View.VISIBLE);
            textDate.setEnabled(true);
        } else {
            textDate.setEnabled(false);
            textDate.setVisibility(View.INVISIBLE);
        }
        return false;
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
        for (TypeAnimals type : TypeAnimals.values()) {
            if (type.getAnimal().equals(animalType)) {
                return type;
            }
        }
        return null;
    }

    //Get dog breed
    private DogBreedEnums getDogBreed(String raceSelected) {
        for (DogBreedEnums dogs : DogBreedEnums.values()) {
            if (dogs.getRace().equals(raceSelected)) {
                Toast.makeText(RegisterPetActivity.this, "Raça: " + raceSelected + ", Value: " + dogs.getValue(), Toast.LENGTH_LONG).show();
                return dogs;
            }
        }
        return null;
    }

    private CatBreedEnums getCatBreed(String raceSelected) {
        for (CatBreedEnums cats : CatBreedEnums.values()) {
            if (cats.getRace().equals(raceSelected)) {
                Toast.makeText(RegisterPetActivity.this, "Raça: " + raceSelected + ", Value: " + cats.getValue(), Toast.LENGTH_LONG).show();
                return cats;
            }
        }
        return null;
    }

    private void updateDate() {
        String myFormat = "dd/MM/YYYY";
        SimpleDateFormat format = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
        textDate.setText(format.format(myCalendar.getTime()));
    }

    private void getCalendar() {
        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(year, monthOfYear, dayOfMonth);
                updateDate();
            }
        };
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterPetActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private PetModel generatePetModel(int id_usuario, String namePet, int agePet, DogBreedEnums race, TypeAnimals typeAnimal, Double weightPet, String observation) {
        PetModel petModel = null;
        return petModel = new PetModel(id_usuario, namePet, agePet, race, typeAnimal, weightPet, observation);
    }

    public boolean checkAllFields() {
        if (name.length() == 0) {
            name.setError("Por favor, insira o nome.");
            return false;
        } else if (age.length() == 0) {
            age.setError("O campo da idade não pode estar vazio.");
            return false;
        }
        return true;
    }

    public int getBreedValue() {
        return breedValue;
    }

    public String getPhoto() {
        return photo;
    }
}