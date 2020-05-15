package com.dixu.PokemonCardsService.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Trainer {


    public enum Sex{
        FEMALE, MALE;



    }
    private String name;
    private Sex sex;
    private String type;
    private List<Card> cards = new ArrayList<>();
    private int coinsCount;

    public Trainer(String name, Sex sex, String type) {
        this.name = name;
        this.sex = sex;
        this.type = type;
    }
    public void addCoins(int count) {
        coinsCount += count;
    }

    public void takeCoins(int count) {
        coinsCount -= count;
    }

    public int getCoinsCount() {
        return coinsCount;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public String getType() {
        return type;
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
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
