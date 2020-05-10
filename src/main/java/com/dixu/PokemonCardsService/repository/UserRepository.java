package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.User;

import java.util.Optional;

public interface UserRepository {

    void saveUser(User user);

    boolean hasThisUser(User user);

    Optional<User> findByMail(String mail);
}
