package com.dixu.PokemonCardsService.service;

import com.dixu.PokemonCardsService.dto.TrainerDTO;
import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    private LoginService loginService;
    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(LoginService loginService, TrainerRepository trainerRepository) {
        this.loginService = loginService;
        this.trainerRepository = trainerRepository;
    }

    public void addTrainer(TrainerDTO trainerDTO) {
        validateAddingAtAll();
        Trainer trainer = createTrainer(trainerDTO);
        trainerRepository.addTrainerForUser(trainer, loginService.getLoggedUser() );
    }

    public void validateAddingAtAll() {
        if (!loginService.isLoggedIn()) {
            throw new TrainerServiceException("Musisz się najpierw zalogować!");
        }
        User loggedUser = loginService.getLoggedUser();
        if (trainerRepository.findTrainerByUser(loggedUser).isPresent()) {
            throw new TrainerServiceException("Stworzyłeś już trenera :) Bierz się za łapanie!");
        }
    }

    private Trainer createTrainer(TrainerDTO trainerDTO) {
        System.out.println(trainerDTO);
        return new Trainer(trainerDTO.getName(),
                Trainer.Sex.valueOf(trainerDTO.getSex().toUpperCase()),
                trainerDTO.getType());
    }
}
