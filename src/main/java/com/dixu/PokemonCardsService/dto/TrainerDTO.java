package com.dixu.PokemonCardsService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TrainerDTO {

    @NotBlank(message = "Należy uzupełnić imię")
    private String name;
    private String sex;
    private String type;
    private int coins;

}
