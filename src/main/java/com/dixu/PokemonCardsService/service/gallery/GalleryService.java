package com.dixu.PokemonCardsService.service.gallery;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.service.trainer.TrainerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GalleryService {

    private TrainerService trainerService;

    public GalleryService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public List<Card> getCards() {
        Trainer loggedTrainer = trainerService.getLoggedTrainer();
        List<Card> cards = loggedTrainer.getCards();
        Collections.sort(cards);
        return cards;
    }
}
