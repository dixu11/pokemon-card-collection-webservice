package com.dixu.PokemonCardsService.service.coins;

import com.dixu.PokemonCardsService.dto.CoinsDTO;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.service.trainer.TrainerService;
import org.springframework.stereotype.Service;

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
