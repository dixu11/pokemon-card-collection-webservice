package com.dixu.PokemonCardsService.service;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private User loggedUser;
    private boolean loggedIn;
    private UserRepository repository;

    public LoginService(UserRepository repository) {
        this.repository = repository;
        logOut();
    }

    public void logIn( UserDTO userDTO) {
        User loggingUser = new User(userDTO.getMail(), userDTO.getPassword());
        Optional<User> accountOptional = repository.findByMail(loggingUser.getMail());
        if (accountOptional.isEmpty()) {
            throw new LoginServiceException("Nie odnaleziono użytkownika o takim mailu","mail");
        }
        if (!accountOptional.get().hasSamePassword(loggingUser)) {
            throw new LoginServiceException("Nieprawidłowe hasło","password");
        }
        loggedIn = true;
        loggedUser = loggingUser;
    }


    public String getLoginStatus() {
        if (loggedIn) {
            return loggedUser.getMail();
        }
        return "Niezalogowany!";
    }

    public void logOut() {
        loggedUser = User.getEmptyUser();
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void validateUserLogged() {
        if (!loggedIn) {
            throw new LoginServiceException("Użytkownik nie zalogowany nie posiada dostępu do podstrony");
        }
    }
}
