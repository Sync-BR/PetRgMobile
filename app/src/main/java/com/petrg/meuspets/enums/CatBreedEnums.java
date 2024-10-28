package com.petrg.meuspets.enums;

public enum CatBreedEnums {
    PERSA("Persa"),

    SIAMES("Siamês"),

    MAINE_COON("Maine Coon"),

    RAGDOLL("Ragdoll"),

    SPHYNX("Sphynx"),

    EXOTICO_PELO_CURTO("Exótico de Pelo Curto"),

    BENGAL("Bengal"),

    ANGORA_TURCO("Angorá Turco"),

    ABISSINIO("Abissínio"),

    BRITISH_SHORTHAIR("British Shorthair"),

    SCOTTISH_FOLD("Scottish Fold"),

    NORUEGUES_DA_FLORESTA("Norueguês da Floresta"),

    SIBERIANO("Siberiano"),

    BIRMANES("Birmanês"),

    BOMBAY("Bombay"),

    CHARTREUX("Chartreux"),

    CORNISH_REX("Cornish Rex"),

    DEVON_REX("Devon Rex"),

    MANX("Manx"),

    ORIENTAL_SHORTHAIR("Oriental Shorthair"),

    SINGAPURA("Singapura"),

    AMERICAN_SHORTHAIR("American Shorthair"),

    SAVANNAH("Savannah"),

    HIMALAIO("Himalaio"),

    TOYGER("Toyger"),

    SRD("Sem Raça Definida");

    private String race;
    private int value;

     CatBreedEnums(String race) {
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
