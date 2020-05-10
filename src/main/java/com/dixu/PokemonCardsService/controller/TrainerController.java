package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.TrainerDTO;
import com.dixu.PokemonCardsService.dto.TrainerResult;
import com.dixu.PokemonCardsService.service.TrainerService;
import com.dixu.PokemonCardsService.service.TrainerServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "trainer-form";
    }

    @PostMapping
    String addNewTrainer(@RequestParam String name, @RequestParam String sex, @RequestParam String type, Model model) {
        try {
            trainerService.addTrainer(new TrainerDTO(name, sex, type));
        } catch (TrainerServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "trainer-form-error";
        }
        model.addAttribute("trainer", new TrainerResult(name, sex, type));
        return "trainer-form-success";
    }
}
