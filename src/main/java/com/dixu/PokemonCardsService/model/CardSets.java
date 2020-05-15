package com.dixu.PokemonCardsService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardSets {
    private List<CardSet> sets;

    public void removeSmallSets() {
        sets = sets.stream()
                .filter(set->set.getTotalCards()>50)
                .collect(Collectors.toList());
    }
}
