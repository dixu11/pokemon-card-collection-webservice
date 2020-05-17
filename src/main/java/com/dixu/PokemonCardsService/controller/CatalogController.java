package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.service.catalog.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogController {

    private CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/catalog")
    public String getCatalogPage(Model model) {
        List<Card> cards = catalogService.getAllCards();
        model.addAttribute("cards", cards);
        return "catalog";
    }
}
