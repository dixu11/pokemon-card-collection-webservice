package com.dixu.PokemonCardsService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileStatsDTO {
    private String name;
    private String type;
    private int coins;
    private int cardsCount;
    private int commonCount;
    private int rareCount;
}
