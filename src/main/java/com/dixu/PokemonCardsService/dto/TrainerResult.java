package com.dixu.PokemonCardsService.dto;

public class TrainerResult {

    private String name;
    private String sex;
    private String type;

    public TrainerResult(String name, String sex, String type) {
        this.name = name;
        this.sex = sex;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex.equals("MALE") ? "kobieta":"mężczyzna";
    }

    public String getType() {
        return type;
    }
}
