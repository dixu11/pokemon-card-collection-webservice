package com.dixu.PokemonCardsService.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CoinsDTO {
    @Min(1)
    @Max(1000)
    private int count;
}
