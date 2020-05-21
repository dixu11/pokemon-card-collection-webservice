package com.dixu.PokemonCardsService.client_http;

import com.dixu.PokemonCardsService.model.Card;
import com.dixu.PokemonCardsService.model.CardSet;
import com.dixu.PokemonCardsService.model.CardSets;
import com.dixu.PokemonCardsService.model.Cards;
import com.dixu.PokemonCardsService.repository.CardRepositoryDb;
import com.dixu.PokemonCardsService.repository.SetsRepositoryDb;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class CardsClient {
    private static final String SETS_ENDPOINT = "https://api.pokemontcg.io/v1/sets";
    private static final String CARDS_ENDPOINT = "https://api.pokemontcg.io/v1/cards?setCode=";
    private RestTemplate restTemplate;
    private SetsRepositoryDb setsRepositoryDb;
    private CardRepositoryDb cardRepositoryDb;
    private AtomicBoolean setsReady = new AtomicBoolean(false);

    public CardsClient(RestTemplate restTemplate, SetsRepositoryDb setsRepositoryDb, CardRepositoryDb cardRepositoryDb) {
        this.restTemplate = restTemplate;
        this.setsRepositoryDb = setsRepositoryDb;
        this.cardRepositoryDb = cardRepositoryDb;
    }

    @PostConstruct
    public void prepareRepositories() {
        System.out.println("Filling DB with cards from Pokemon API");
        prepareSetsRepository();
        prepareCardsRepository();
    }

    private void prepareSetsRepository() {
        System.out.println("Filling set names from Pokemon TCG Api");
        if (setsRepositoryDb.count() == 0) {
            downloadAndSaveSetsOnAnotherThread();
        } else {
            System.out.println("Sets already filled");
            setsReady.set(true);
        }
    }

    private void downloadAndSaveSetsOnAnotherThread() {
        new Thread(() -> {
            System.out.println("Downloading missing sets from: "+ SETS_ENDPOINT);
            CardSets cardSets = restTemplate.getForObject(SETS_ENDPOINT, CardSets.class);
            cardSets.removeSmallSets();
            setsRepositoryDb.saveAll(cardSets.getSets());
            setsReady.set(true);
            System.out.println("Sets downloaded");
        }).start();
    }

    private void prepareCardsRepository() {
        new Thread(()->{
            System.out.println("Cards downloader waits for sets list to be downloaded");
            waitUntilSetsReady();
            downloadAllCards();
        }).start();
    }

    private void downloadAllCards() {
        System.out.println("Downloading cards");
        List<CardSet> allSets = setsRepositoryDb.findAll();
        for (CardSet set : allSets) {
            downloadSet(set);
        }
    }

    private void downloadSet(CardSet set) {
        if (set.isCardsDownloaded()) {
            System.out.println(set.getCode() + " already in DB!");
            return;
        }
        try {
            executeCardsDownload(set);
        } catch (HttpServerErrorException e) {
            System.err.printf("Error: \n{%s}\n while downloading set: %s\n", e.getMessage(), set.getCode());
        }
    }

    private void executeCardsDownload(CardSet set) {
        new Thread(()->{
            try{
                List<Card> cards = getCardsFromSet(set);
                cardRepositoryDb.saveAll(cards);
                set.setCardsDownloaded(true);
                setsRepositoryDb.save(set);
            }catch (HttpServerErrorException e){
                System.err.println("Server refused to download set: "+ set.getCode());
            }catch (Exception e){
                System.out.println("Unknown error for set: "+ set.getCode());
            }
        }).start();
    }

    private void waitUntilSetsReady() {
        while (!setsReady.get()) {
            try {
                System.out.println("are sets ready?");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Card> getCardsFromSet(CardSet cardSet) {
        int page = 0;
        List<Card> cards = new ArrayList<>();
        do {
            page++;
            cards.addAll(getCardsOnePage(cardSet.getCode(), page));
        }while (cardSet.getTotalCards()> page*100);
        System.out.println("Adding pack of " + cards.size() + " cards\n");
        return cards;
    }

    private List<Card> getCardsOnePage(String code, int page) {
        System.out.println("Downloading cards with: " + CARDS_ENDPOINT + code + "&page=" + page);
        Cards cardsObject = restTemplate.getForObject(CARDS_ENDPOINT + code + "&page=" + page, Cards.class);
        System.out.println("Downloaded " + cardsObject.getCards().size() + " cards");
        return cardsObject.getCards();
    }
}
//http://www.javaoutofbounds.com/consuming-restful-web-service-springboot/