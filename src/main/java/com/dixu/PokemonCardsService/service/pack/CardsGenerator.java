package com.dixu.PokemonCardsService.service.pack;

import com.dixu.PokemonCardsService.model.Card;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CardsGenerator {

    private String[] pokemons = {
            "Gengar","Bulbasaur","Charizard","Pidgey","Rattata",
            "Pikachu","Ekans","Caterpie","Blastoise","Nidoran",
            "Voltorb","Zubat","Odish","Meowth","Persian",
            "Psyduck","Mankey","Mewtwo","Slowpoke","Gastly",
            "Krabby","Hypno","Exeggcute","Krabby","Onix",
            "Koffing","Tangela","Horsea","Staryu","Jinx",
            "Tauros","Lapras","Gyardos","Eevee","Snorlax",
            "Zapdos","Moltres","Articuno","Dratini","Mew",
    };

    public List<Card> generateStandardPackCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int index = new Random().nextInt(pokemons.length);
            cards.add(new Card(pokemons[index]));
        }
        return cards;
    }
}
