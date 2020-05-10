package com.dixu.PokemonCardsService.service;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserRepository repository;

    @Autowired
    public RegisterService(UserRepository repository) {
        this.repository = repository;
    }

    public void register(UserDTO userDTO) {
        validateUser(userDTO);
        repository.saveUser(new User(userDTO.getMail(),userDTO.getPassword()));
    }


    private void validateUser(UserDTO userDTO) {
        if (userDTO.getMail().isBlank() || userDTO.getPassword().isBlank()) {
            throw new UserServiceException("Wpisz mail oraz hasło!");
        }
        if (userDTO.getPassword().length() < 5) {
            throw new UserServiceException("Hasło musi mieć minimum 5 znaków!");
        }
        if (!userDTO.getMail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new UserServiceException("Wpisz poprawny mail!");
        }
    }


}
