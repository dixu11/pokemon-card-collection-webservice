package com.dixu.PokemonCardsService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card implements Comparable<Card> {

    @Id
    private String id;
    private String name;
    private String rarity;
    private String nationalPokedexNumber;
    private String imageUrl;
    @ElementCollection
    private List<String> types;

    @Override
    public int compareTo(Card o) {
        return id.compareTo(o.id);
    }
}
