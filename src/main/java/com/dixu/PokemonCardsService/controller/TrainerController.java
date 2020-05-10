package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.TrainerDTO;
import com.dixu.PokemonCardsService.dto.TrainerResult;
import com.dixu.PokemonCardsService.service.TrainerService;
import com.dixu.PokemonCardsService.service.TrainerServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.dixu.PokemonCardsService.model.Trainer.Sex.MALE;

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
        TrainerDTO trainer = new TrainerDTO();
        String defaultValue = "male";
        trainer.setSex(defaultValue);
        model.addAttribute("trainer", trainer);
        return "trainer-form";
    }

    @PostMapping
    String addNewTrainer(@Valid @ModelAttribute("trainer") TrainerDTO trainer, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "trainer-form";
        }
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