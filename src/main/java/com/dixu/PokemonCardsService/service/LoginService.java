package com.dixu.PokemonCardsService.service;

import com.dixu.PokemonCardsService.repository.UserRepository;
import com.dixu.PokemonCardsService.user.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private User loggedUser;
    private boolean loggedIn;
    private UserRepository repository;

    public LoginService(UserRepository repository) {
        this.repository = repository;
        loggedUser = User.getEmptyUser();
        loggedIn = false;
    }

    public void logIn(User user) {
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
}
