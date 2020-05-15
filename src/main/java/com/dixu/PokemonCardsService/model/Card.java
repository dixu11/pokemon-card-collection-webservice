package com.dixu.PokemonCardsService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card implements Comparable<Card> {

    private String name;
    private String id;
    //types array
    private String rarity;
    private String nationalPokedexNumber;
    private String imageUrl;
    private List<String> types;

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
