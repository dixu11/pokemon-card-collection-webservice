package com.dixu.PokemonCardsService.service;

import com.dixu.PokemonCardsService.dto.CoinsDTO;
import com.dixu.PokemonCardsService.model.Trainer;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CoinsService {

    private TrainerService trainerService;

    public CoinsService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public void addCoins(CoinsDTO count) {
        Trainer trainer = trainerService.getLoggedTrainer();
        trainer.addCoins(count.getCount());
    }
}
