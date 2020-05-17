package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.HomeDTO;
import com.dixu.PokemonCardsService.service.home.HomeService;
import com.dixu.PokemonCardsService.service.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

    private HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    String getHomePage(Model model) {
        HomeDTO status = homeService.getLoginStatus();
        model.addAttribute("status", status);
        return "index";
    }
}
