package com.dixu.PokemonCardsService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardSet {
    private String code;
    private int totalCards;
}
