package com.dixu.PokemonCardsService.model;

import java.util.List;

public class TrainerStats {
    private Trainer trainer;
    private List<Card> cards;

    public TrainerStats(Trainer trainer) {
        this.trainer = trainer;
        cards = trainer.getCards();
    }

    public int getCardsCount() {
        return cards.size();
    }

    public int getCardsOfRarity(String rarity) {
        return (int) cards.stream()
                .filter(card -> card.getRarity().equalsIgnoreCase(rarity))
                .count();
    }

    public int getCoinsCount() {
        return trainer.getCoinsCount();
    }

    public String getName() {
        return trainer.getName();
    }

    public String getTrainerType() {
        return trainer.getType();
    }

    public int countNotOfRarity(String rarity){
        return (int) cards.stream()
                .filter(card -> !card.getRarity().equalsIgnoreCase(rarity))
                .count();
    }
}
