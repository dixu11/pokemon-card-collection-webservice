package com.dixu.PokemonCardsService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Wpisz mail!")
    @Email(message = "Wpisz poprawny mail!")
    private String mail;

    @NotBlank(message = "Wpisz hasło!")
    @Size(min = 5, message = "Hasło musi mieć minimum 5 znaków!")
    private String password;

}
