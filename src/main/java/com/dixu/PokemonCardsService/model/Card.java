package com.dixu.PokemonCardsService.model;

public class Card implements Comparable<Card> {

    private String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Card o) {
        return name.compareTo(o.name);
    }
}
