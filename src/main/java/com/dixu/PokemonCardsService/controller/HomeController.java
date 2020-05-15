package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.service.LoginService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

    private LoginService loginService;

    public HomeController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    String getHomePage(Model model) {
        model.addAttribute("login_mail", loginService.getLoginStatus());
        model.addAttribute("logged", loginService.isLoggedIn());
        return "index";
    }
}
