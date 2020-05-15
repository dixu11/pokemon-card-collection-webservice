package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryTrainerRepository implements TrainerRepository {
    private Map<User, Trainer> trainers;
    @Value("${testmode}")
    private boolean test;

    public InMemoryTrainerRepository() {
        trainers = new HashMap<>();
    }

    @PostConstruct
    private void addStartingData() {
        if (test) {
            saveTrainerForUser(new Trainer("Dixu", Trainer.Sex.FEMALE,"fire"),new User("d@d.pl","12345"));
        }
    }

    @Override
    public Optional<Trainer> findTrainerByUser(User user) {
        if (trainers.containsKey(user)) {
            return Optional.of(trainers.get(user));
        }
        return Optional.empty();
    }


    @Override
    public void saveTrainerForUser(Trainer trainer, User user) {
        trainers.put(user, trainer);
    }


}
