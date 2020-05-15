package com.dixu.PokemonCardsService.service.pack;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.model.CardSet;
import com.dixu.PokemonCardsService.model.CardSets;
import com.dixu.PokemonCardsService.client_http.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class CardsGenerator {

    private CardsClient cardsClient;
    private Random random = new Random();

    public CardsGenerator(CardsClient cardsClient) {
        this.cardsClient = cardsClient;
    }

 /*   private String[] pokemons = {
            "Gengar", "Bulbasaur", "Charizard", "Pidgey", "Rattata",
            "Pikachu", "Ekans", "Caterpie", "Blastoise", "Nidoran",
            "Voltorb", "Zubat", "Odish", "Meowth", "Persian",
            "Psyduck", "Mankey", "Mewtwo", "Slowpoke", "Gastly",
            "Krabby", "Hypno", "Exeggcute", "Krabby", "Onix",
            "Koffing", "Tangela", "Horsea", "Staryu", "Jinx",
            "Tauros", "Lapras", "Gyardos", "Eevee", "Snorlax",
            "Zapdos", "Moltres", "Articuno", "Dratini", "Mew",
    };*/

    public List<Card> generateStandardPackCards() {
        List<Card> cards = new ArrayList<>();
        List<Card> setCards = cardsClient.getCardsFromRandomSet();
        for (int i = 0; i < 5; i++) {
            cards.add(pickCommonCard(setCards));
        }
        return cards;
    }

    private Card pickCommonCard(List<Card> cards) {
        List<Card> commons = cards.stream()
                .filter(card -> card.getRarity()!=null && card.getRarity().equalsIgnoreCase("common"))
                .collect(Collectors.toList());
        if (commons.isEmpty()) {
            return cards.get(0);
        }
        return commons.get(random.nextInt(commons.size()));
    }


}
