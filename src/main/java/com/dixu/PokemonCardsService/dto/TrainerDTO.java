package com.dixu.PokemonCardsService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainerDTO {

    private String name;
    private String sex;
    private String type;

}
