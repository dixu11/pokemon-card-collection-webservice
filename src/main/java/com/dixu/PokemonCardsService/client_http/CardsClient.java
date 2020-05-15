package com.dixu.PokemonCardsService.client_http;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.model.CardSet;
import com.dixu.PokemonCardsService.model.CardSets;
import com.dixu.PokemonCardsService.model.Cards;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Component
public class CardsClient {
    private static final String SETS_ENDPOINT = "https://api.pokemontcg.io/v1/sets";
    private static final String CARDS_ENDPOINT = "https://api.pokemontcg.io/v1/cards?setCode=";
    private CardSets cardSets;

    public CardsClient() {

    }

    public CardSets getSets() {
        if (cardSets == null) {
            RestTemplate restTemplate = new RestTemplate();
            cardSets = restTemplate.getForObject(SETS_ENDPOINT, CardSets.class);
            cardSets.removeSmallSets();
        }
        return cardSets;
    }


    public List<Card> getCardsFromRandomSet() {
        Random random = new Random();
        List<CardSet> setsList = getSets().getSets();
        int randomIdex = random.nextInt(setsList.size());
        CardSet randomSet = setsList.get(randomIdex);
       return getCardsFromSet(randomSet.getCode());
    }

    public List<Card> getCardsFromSet(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Cards cardsObject = restTemplate.getForObject(CARDS_ENDPOINT + code, Cards.class);
        return cardsObject.getCards();
    }
}
//http://www.javaoutofbounds.com/consuming-restful-web-service-springboot/