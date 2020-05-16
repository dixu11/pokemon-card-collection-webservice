package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.service.gallery.GalleryService;
import com.dixu.PokemonCardsService.service.login.LoginServiceException;
import com.dixu.PokemonCardsService.service.trainer.TrainerServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GalleryController {

    private GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/gallery")
    public String getGalleryPage(Model model) {
        List<Card> cards;
        try {
           cards = galleryService.getCards();
        }catch (TrainerServiceException | LoginServiceException e){
            model.addAttribute("error", e.getMessage());
            return "no-access";
        }
        model.addAttribute("cards", cards);
        return "gallery";
    }
}
