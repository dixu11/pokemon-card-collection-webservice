package com.dixu.PokemonCardsService.service.pack;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.repository.CardRepositoryDb;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class CardsGenerator {

    public static final String NO_CARDS_ERROR_MESSAGE = "Problem with connection to cards API, try again later";
    private CardRepositoryDb cardRepositoryDb;
    private Random random = new Random();
    private List<Card> allCards = new ArrayList<>();

    public CardsGenerator(CardRepositoryDb cardRepositoryDb) {
        this.cardRepositoryDb = cardRepositoryDb;
    }

    @PostConstruct
    private void fillInMemoryCardsCollection() {
        allCards = cardRepositoryDb.findAll();
    }

    public List<Card> generateStandardPackCards() {
        if (allCards.isEmpty()) {
            fillInMemoryCardsCollection();
        }
        List<Card> packCards = pick5Cards();
        Collections.shuffle(packCards);
        return packCards;
    }

    private List<Card> pick5Cards() {
        List<Card> packCards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            packCards.add(pickCommonCard());
        }
        packCards.add(pickUncommonCard());
        return packCards;
    }

    private Card pickUncommonCard() {
        List<Card> uncommon = allCards.stream()
                .filter(card -> card.getRarity()!=null && !card.getRarity().equalsIgnoreCase("common"))
                .collect(Collectors.toList());
        if (uncommon.isEmpty()) {
            throw new PackOpenServiceException(NO_CARDS_ERROR_MESSAGE);
        }
        return uncommon.get(random.nextInt(uncommon.size()));
    }

    private Card pickCommonCard() {
        List<Card> commons = allCards.stream()
                .filter(card -> card.getRarity()!=null && card.getRarity().equalsIgnoreCase("common"))
                .collect(Collectors.toList());
        if (commons.isEmpty()) {
            throw new PackOpenServiceException(NO_CARDS_ERROR_MESSAGE);
        }
        return commons.get(random.nextInt(commons.size()));
    }


}
