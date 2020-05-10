package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.user.User;

public interface UserRepository {

    void saveUser(User user);

    boolean hasThisUser(User user);

}
