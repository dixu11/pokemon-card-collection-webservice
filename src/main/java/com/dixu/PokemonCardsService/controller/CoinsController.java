package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.CoinsDTO;
import com.dixu.PokemonCardsService.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CoinsController {

    private TrainerService trainerService;
    private CoinsService coinsService;

    public CoinsController(TrainerService trainerService, CoinsService coinsService) {
        this.trainerService = trainerService;
        this.coinsService = coinsService;
    }

    @GetMapping("/coins")
    public String getCoinsForm(Model model) {
        model.addAttribute("coins", new CoinsDTO());
        try {
            trainerService.validateHasTrainer();
        }catch (TrainerServiceException | LoginServiceException e){
            model.addAttribute("error", e.getMessage());
            return "no-access";
        }
        return "coins-form";
    }

    @PostMapping("/coins")
    public String addCoins(@Valid @ModelAttribute("coins") CoinsDTO coins, BindingResult result) {
        if (result.hasErrors()) {
            return "coins-form";
        }
        coinsService.addCoins(coins);
        return "coins-success";
    }

}
