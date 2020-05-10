package com.dixu.PokemonCardsService.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String mail;
    private String password;

    public static User getEmptyUser() {
        return new User("","");
    }
}
