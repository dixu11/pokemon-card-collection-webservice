package com.dixu.PokemonCardsService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String mail;
    private String password;
}
