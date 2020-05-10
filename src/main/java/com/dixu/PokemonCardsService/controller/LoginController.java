package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.service.LoginService;
import com.dixu.PokemonCardsService.service.LoginServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    String getLoginPage() {
        return "login-form";
    }

    @PostMapping
    String logIn(@RequestParam String mail, @RequestParam String pass, Model model) {
        try {
            loginService.logIn(new UserDTO(mail, pass));
        } catch (LoginServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "login-failed";
        }
        return "login-success";
    }

}
