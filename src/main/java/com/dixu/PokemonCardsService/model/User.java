package com.dixu.PokemonCardsService.model;

import com.dixu.PokemonCardsService.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    private String mail;
    private String password;
    private String role;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
        role = "ROLE_USER";
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
