package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.model.User;

import java.util.Optional;

public interface TrainerRepository {
    Optional<Trainer> findTrainerByUser(User user);

    void saveTrainerForUser(Trainer trainer, User user);

}
