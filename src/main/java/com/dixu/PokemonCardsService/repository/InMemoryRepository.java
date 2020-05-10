package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryRepository implements UserRepository {
    private List<User> users;

    public InMemoryRepository() {
        users = new ArrayList<>();
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
        System.out.println("Zapisano usera!");
        System.out.println(users);
    }

    @Override
    public boolean hasThisUser(User user) {
        return users.contains(user);
    }
}
