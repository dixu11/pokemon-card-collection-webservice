package com.dixu.PokemonCardsService.service.auctions;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.service.trainer.TrainerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuctionsService {

    private TrainerService trainerService;

    public AuctionsService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public List<Card> getLoggedUserCards() { // todo only rare!
        Trainer loggedTrainer = trainerService.getLoggedTrainer();
        List<Card> cards = loggedTrainer.getCards();
        Collections.sort(cards);
        return cards;
    }
}
