package com.dixu.PokemonCardsService.model;

import com.dixu.PokemonCardsService.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
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
        return Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }

    public static User getEmptyUser() {
        return new User("", "");
    }

    public String getMail() {
        return mail;
    }

    public boolean hasSamePassword(User loggingUser) {
        return loggingUser.password.equals(password);
    }
}
