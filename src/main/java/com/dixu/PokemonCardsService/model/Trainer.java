package com.dixu.PokemonCardsService.model;

import lombok.Data;

import java.util.Objects;


public class Trainer {
    public enum Sex{
        FEMALE, MALE
    }

    private String name;
    private Sex sex;

    private String type;

    public Trainer(String name, Sex sex, String type) {
        this.name = name;
        this.sex = sex;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(name, trainer.name) &&
                sex == trainer.sex &&
                Objects.equals(type, trainer.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sex, type);
    }
}
