package com.dixu.PokemonCardsService.service.catalog;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.repository.CardRepositoryDb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private CardRepositoryDb cardRepositoryDb;

    public CatalogService(CardRepositoryDb cardRepositoryDb) {
        this.cardRepositoryDb = cardRepositoryDb;
    }

    public List<Card> getAllCards() {
        return cardRepositoryDb.findAll();
    }
}
