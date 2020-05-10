package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryTrainerRepository implements TrainerRepository {
    private Map<User, Trainer> trainers;

    public InMemoryTrainerRepository() {
        trainers = new HashMap<>();
    }

    @Override
    public Optional<Trainer> findTrainerByUser(User user) {
        if (trainers.containsKey(user)) {
            return Optional.of(trainers.get(user));
        }
        return Optional.empty();
    }


    @Override
    public void addTrainerForUser(Trainer trainer, User user) {
        trainers.put(user, trainer);
    }


}
