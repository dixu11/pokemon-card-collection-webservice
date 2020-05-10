package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("logout")
public class LogoutController {
    private LoginService loginService;

    @Autowired
    public LogoutController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    String getLogoutPage() {
        loginService.logOut();
        return "redirect:";
    }
}
