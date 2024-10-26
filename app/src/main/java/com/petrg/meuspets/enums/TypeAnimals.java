package com.petrg.meuspets.enums;


public enum TypeAnimals {
    cat("Gato"),
    dog("Cachorro");
    private String animal;
    private int value;
    TypeAnimals(String animal) {
        this.animal = animal;
        this.value = ordinal();
    }

    public String getAnimal() {
        return animal;
    }

    public int getValue() {
        return value;
    }
}