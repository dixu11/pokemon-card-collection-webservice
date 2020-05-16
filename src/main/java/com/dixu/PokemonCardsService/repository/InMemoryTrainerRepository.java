package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.model.Trainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class InMemoryTrainerRepository implements TrainerRepository {
    private Set<Trainer> trainers;
    @Value("${testmode}")
    private boolean test;

    public InMemoryTrainerRepository() {
        trainers = new HashSet<>();
    }

    @PostConstruct
    private void addStartingData() {
        if (test) {
            save(new Trainer("d@d.pl", "Dixu", Trainer.Sex.FEMALE, "fire"));
        }
    }

    @Override
    public Optional<Trainer> findTrainerByMail(String mail) {
        return trainers.stream()
                .filter(trainer -> trainer.getMail().equals(mail))
                .findAny();
    }

    @Override
    public Trainer save(Trainer trainer) {
        trainers.add(trainer);
        return trainer;
    }

}
