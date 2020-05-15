package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.service.login.LoginServiceException;
import com.dixu.PokemonCardsService.service.pack.PackOpenService;
import com.dixu.PokemonCardsService.service.pack.PackOpenServiceException;
import com.dixu.PokemonCardsService.service.trainer.TrainerService;
import com.dixu.PokemonCardsService.service.trainer.TrainerServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class PackController {

    private TrainerService trainerService;
    private PackOpenService packOpenService;

    public PackController(TrainerService trainerService, PackOpenService packOpenService) {
        this.trainerService = trainerService;
        this.packOpenService = packOpenService;
    }

    @GetMapping("/pack")
    public String getPackOpenPage(Model model) {
        try {
            trainerService.validateHasTrainer();
        } catch (TrainerServiceException | LoginServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "no-access";
        }
        Trainer trainer = trainerService.getLoggedTrainer();
        model.addAttribute("trainer", trainer);
        return "pack-open";
    }

    @PostMapping("/pack")
    public String openPack(Model model) {
        List<Card> cards = packOpenService.openStandardPack();
        model.addAttribute("cards", cards);
        return "new-cards";
    }


}
