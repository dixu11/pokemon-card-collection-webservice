package com.dixu.PokemonCardsService.service.login;

public class LoginServiceException extends RuntimeException {

    private String field = "";
    public LoginServiceException(String message) {
        super(message);
    }

    public LoginServiceException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
