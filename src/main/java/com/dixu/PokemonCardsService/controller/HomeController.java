package com.dixu.PokemonCardsService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

    @GetMapping
    String getHomePage() {
        return "index";
    }
}