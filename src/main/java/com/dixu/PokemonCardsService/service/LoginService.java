package com.dixu.PokemonCardsService.service;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private User loggedUser;
    private boolean loggedIn;
    private UserRepository repository;

    public LoginService(UserRepository repository) {
        this.repository = repository;
        logOut();
    }

    public void logIn(UserDTO userDTO) {
        User user = new User(userDTO.getMail(), userDTO.getPassword());
        if (!repository.hasThisUser(user)) {
            throw new LoginServiceException("Nie odnaleziono użytkownika o takim mailu i haśle, spróbuj jeszcze raz");
        }
        loggedIn = true;
        loggedUser = user;
    }


    public String getLoginStatus() {
        if (loggedIn) {
            return "Zalogowany jako: " + loggedUser.getMail();
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

}
