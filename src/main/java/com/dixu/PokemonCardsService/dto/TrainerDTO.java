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

    //https://4programmers.net/Forum/Java/307762-dto_czy_dobrze_rozumiem
}
