package com.petrg.meuspets.enums;

public enum PetBreedEnums {
    AKITA_INU("Akita Inu"),
    BEAGLE("Beagle"),
    BICHON_FRISE("Bichon Frisé"),
    BULLY_PIT("Bully Pit"),
    CANE_CORSO("Cane Corso"),
    CHIHUAHUA("Chihuahua"),
    COCKER_SPANIEL("Cocker Spaniel"),
    DACHSHUND("Dachshund"),
    DOBERMAN("Doberman"),
    FOX_PAULISTINHA("Fox Paulistinha"),
    GOLDEN_RETRIEVER("Golden Retriever"),
    LABRADOR_RETRIEVER("Labrador Retriever"),
    MALTES("Maltês"),
    PASTOR_ALEMAO("Pastor Alemão"),
    PEKINGESE("Pekingese"),
    POMERANIAN("Pomeranian"),
    POODLE("Poodle"),
    ROTTWEILER("Rottweiler"),
    SCHNAUZER("Schnauzer"),
    SHIH_TZU("Shih Tzu"),
    YORKSHIRE_TERRIER("Yorkshire Terrier"),
    PIT_BULL("Pit Bull"),
    BORDER_COLLIE("Border Collie"),
    BASSET_HOUND("Basset Hound"),
    FRENCH_BULLDOG("French Bulldog"),
    CAVALIER_KING_CHARLES_SPANIEL("Cavalier King Charles Spaniel"),
    CATAHOULA("Catahoula"),
    CHOW_CHOW("Chow Chow"),
    JACK_RUSSELL_TERRIER("Jack Russell Terrier"),
    MASTIFF("Mastiff"),
    PIT_BULL_TERRIER("Pit Bull Terrier"),
    SALUKI("Saluki"),
    WHIPPET("Whippet");
    private String race;
    private int value;

    PetBreedEnums(String race){
        this.race = race;
        this.value = ordinal();
    }

    public String getRace() {
        return race;
    }

    public int getValue() {
        return value;
    }
}
