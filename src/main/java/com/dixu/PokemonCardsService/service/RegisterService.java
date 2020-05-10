package com.dixu.PokemonCardsService.service;

import com.dixu.PokemonCardsService.repository.UserRepository;
import com.dixu.PokemonCardsService.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserRepository repository;

    @Autowired
    public RegisterService(UserRepository repository) {
        this.repository = repository;
    }

    public void register(User user) {
        validateUser(user);
        repository.saveUser(user);
    }


    private void validateUser(User user) {
        if (user.getMail().isBlank() || user.getPassword().isBlank()) {
            throw new UserServiceException("Wpisz mail oraz hasło!");
        }
        if (user.getPassword().length() < 5) {
            throw new UserServiceException("Hasło musi mieć minimum 5 znaków!");
        }
        if (!user.getMail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new UserServiceException("Wpisz poprawny mail!");
        }
    }


}
