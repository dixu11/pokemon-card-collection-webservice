package com.dixu.PokemonCardsService.model;

import com.dixu.PokemonCardsService.dto.UserDTO;

import java.util.Objects;

public class User {

    private String mail;
    private String password;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(mail, user.mail) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, password);
    }

    public static User getEmptyUser() {
        return new User("", "");
    }

    public String getMail() {
        return mail;
    }
}