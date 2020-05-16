package com.dixu.PokemonCardsService.service.pack;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.repository.TrainerRepository;
import com.dixu.PokemonCardsService.service.trainer.TrainerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackOpenService {

    private static final int STANDARD_PACK_COST = 100;

    private TrainerService trainerService;
    private CardsGenerator cardsGenerator;
    private TrainerRepository trainerRepository;

    public PackOpenService(TrainerService trainerService, CardsGenerator cardsGenerator, TrainerRepository trainerRepository) {
        this.trainerService = trainerService;
        this.cardsGenerator = cardsGenerator;
        this.trainerRepository = trainerRepository;
    }

    public List<Card> openStandardPack() {
        Trainer trainer = trainerService.getLoggedTrainer();
        trainer.takeCoins(STANDARD_PACK_COST);
        List<Card> cards = cardsGenerator.generateStandardPackCards();
        trainer.addCards(cards);
        trainerRepository.save(trainer);
        return cards;
    }
}
