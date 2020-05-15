package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.service.login.LoginService;
import com.dixu.PokemonCardsService.service.login.LoginServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    String getLoginPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login-form";
    }

    @PostMapping
    String logIn(@Valid @ModelAttribute("user") UserDTO user, BindingResult errors) {
        if (errors.hasErrors()) {
            return "login-form";
        }
        try {
            loginService.logIn(user);
        } catch (LoginServiceException e) {
            errors.addError(new FieldError("user", e.getField(), e.getMessage()));
            return "login-form";
        }
        return "redirect:";
    }

}
