package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.TrainerDTO;
import com.dixu.PokemonCardsService.dto.TrainerResult;
import com.dixu.PokemonCardsService.service.TrainerService;
import com.dixu.PokemonCardsService.service.TrainerServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    String getNewTrainerForm(Model model) {
        try {
            trainerService.validateAddingAtAll();
        } catch (TrainerServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "trainer-form-error";
        }
        model.addAttribute("trainer", new TrainerDTO());
        return "trainer-form";
    }

    @PostMapping
    String addNewTrainer(@ModelAttribute TrainerDTO trainer, Model model) {
        try {
            trainerService.addTrainer(trainer);
        } catch (TrainerServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "trainer-form-error";
        }
        model.addAttribute("trainer", new TrainerResult(trainer.getName(), trainer.getSex(), trainer.getType()));
        return "trainer-form-success";
    }
}
