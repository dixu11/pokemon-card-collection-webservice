package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.service.auctions.AuctionsService;
import com.dixu.PokemonCardsService.service.login.LoginServiceException;
import com.dixu.PokemonCardsService.service.trainer.TrainerServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auctions")
public class AuctionsController {

    private AuctionsService auctionsService;

    public AuctionsController(AuctionsService auctionsService) {
        this.auctionsService = auctionsService;
    }

    @GetMapping()
    public String getMarket(Model model) {
        List<Card> cards;
        try {
            cards = auctionsService.getCards();
        }catch (TrainerServiceException | LoginServiceException e){
            model.addAttribute("error", e.getMessage());
            return "no-access";
        }
        model.addAttribute("cards", cards);
        return "auctions";
    }
}
