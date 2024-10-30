package com.petrg.meuspets.model;

import com.petrg.meuspets.enums.CatBreedEnums;
import com.petrg.meuspets.enums.DogBreedEnums;
import com.petrg.meuspets.enums.TypeAnimals;

import java.io.Serializable;
import java.time.LocalDate;

public class PetModel implements Serializable {

    private int id;
    private int id_usuario;
    private String namePet;
    private int agePet;
    private String race;
    private DogBreedEnums raceDog;
    private CatBreedEnums raceCat;
    private TypeAnimals typeAnimal;
    private Double weightPet;
    private String observation;
    private String photo;
    private LocalDate castratedDate;

    public PetModel(String photo) {
        this.photo = photo;
    }

    public PetModel(int id_usuario, String namePet, int agePet, DogBreedEnums race, TypeAnimals typeAnimal, Double weightPet, String observation) {
        this.id_usuario = id_usuario;
        this.namePet = namePet;
        this.agePet = agePet;
        this.raceDog = race;
        this.typeAnimal = typeAnimal;
        this.weightPet = weightPet;
        this.observation = observation;
        this.race = race.getRace();
    }

    public PetModel(int id_usuario, String namePet, int agePet, CatBreedEnums race, TypeAnimals typeAnimal, Double weightPet, String observation) {
        this.id_usuario = id_usuario;
        this.namePet = namePet;
        this.agePet = agePet;
        this.raceCat = race;
        this.typeAnimal = typeAnimal;
        this.weightPet = weightPet;
        this.observation = observation;
        this.race = race.getRace();
    }

    @Override
    public String toString() {
        return "PetModel{" +
                "id=" + id +
                ", id_usuario=" + id_usuario +
                ", namePet='" + namePet + '\'' +
                ", agePet=" + agePet +
                ", race='" + race + '\'' +
                ", raceDog=" + raceDog +
                ", raceCat=" + raceCat +
                ", typeAnimal=" + typeAnimal +
                ", weightPet=" + weightPet +
                ", observation='" + observation + '\'' +
                ", photo='" + photo + '\'' +
                ", castratedDate=" + castratedDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public int getAgePet() {
        return agePet;
    }

    public void setAgePet(int agePet) {
        this.agePet = agePet;
    }

    public DogBreedEnums getRace() {
        return raceDog;
    }

    public void setRace(DogBreedEnums race) {
        this.race = race.getRace();
        this.raceDog = race;
    }

    public CatBreedEnums getRaceCat() {
        return raceCat;
    }

    public void setRaceCat(CatBreedEnums raceCat) {
        this.race = raceCat.getRace();

        this.raceCat = raceCat;
    }

    public TypeAnimals getTypeAnimal() {
        return typeAnimal;
    }

    public void setTypeAnimal(TypeAnimals typeAnimal) {
        this.typeAnimal = typeAnimal;
    }

    public Double getWeightPet() {
        return weightPet;
    }

    public void setWeightPet(Double weightPet) {
        this.weightPet = weightPet;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getCastratedDate() {
        return castratedDate;
    }

    public void setCastratedDate(LocalDate castratedDate) {
        this.castratedDate = castratedDate;
    }
}
