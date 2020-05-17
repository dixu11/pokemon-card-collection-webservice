package com.dixu.PokemonCardsService.service.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginService {

    public boolean isUserLogged() {
        return getLoggedUserDetails().isPresent();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext()
                .getAuthentication();
    }

    public String getLoggedUserMail() {
        Optional<UserDetails> userOptional = getLoggedUserDetails();
        if (userOptional.isEmpty()) {
            throw new NoSuchElementException("User not found!");
        }
        return userOptional.get()
                .getUsername();
    }

    private Optional<UserDetails> getLoggedUserDetails() {
        Authentication authentication = getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            return Optional.of((UserDetails) authentication.getPrincipal());
        }
        return Optional.empty();
    }
}
