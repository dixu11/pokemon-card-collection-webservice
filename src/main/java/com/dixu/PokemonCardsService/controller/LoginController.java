package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.service.LoginService;
import com.dixu.PokemonCardsService.service.LoginServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    String logIn(@ModelAttribute UserDTO user, Model model) {
        try {
            loginService.logIn(user);
        } catch (LoginServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "login-failed";
        }
        return "login-success";
    }

}
