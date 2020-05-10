package com.dixu.PokemonCardsService.service;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    private UserRepository repository;

    @Autowired
    public RegisterService(UserRepository repository) {
        this.repository = repository;
    }

    public void register(UserDTO userDTO) {
        User user = new User(userDTO.getMail(),userDTO.getPassword());
        checkIfAlreadyRegistered(user);
        repository.saveUser(new User(userDTO.getMail(),userDTO.getPassword()));
    }

    private void checkIfAlreadyRegistered(User user) {
        Optional<User> sameUser = repository.findByMail(user.getMail());
        if (sameUser.isPresent()) {
            throw new RegisterServiceException("Wybrałeś maila, którego mamy już w bazie! Spróbuj się zalogować.");
        }
    }

}
