package com.dixu.PokemonCardsService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardSet {
    @Id
    private String code;
    private int totalCards;
    @JsonIgnore
    private boolean cardsDownloaded = false;
}
