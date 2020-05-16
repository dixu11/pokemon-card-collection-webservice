package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryUserRepository implements UserRepository {
    private List<User> users;

    public InMemoryUserRepository() {
        users = new ArrayList<>();
    }



    @Override
    public Optional<User> findByMail(String mail) {
        return users.stream()
                .filter(user -> user.getMail().equals(mail))
                .findAny();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }


}
