package com.dixu.PokemonCardsService.service.trainer;

import com.dixu.PokemonCardsService.dto.TrainerDTO;
import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.TrainerRepository;
import com.dixu.PokemonCardsService.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TrainerService {
    public static final int STARTING_COINS = 500;
    private LoginService loginService;
    private TrainerRepository trainerRepository;


    @Autowired
    public TrainerService(LoginService loginService, TrainerRepository trainerRepository) {
        this.loginService = loginService;
        this.trainerRepository = trainerRepository;
    }

    public Trainer createNewTrainer(TrainerDTO trainerDTO) {
        validateAddingAtAll();
        Trainer trainer = createTrainer(trainerDTO);
        addStartingCoins(trainer);
        trainerRepository.saveTrainerForUser(trainer, loginService.getLoggedUser() );
        return trainer;
    }

    public void addStartingCoins(Trainer trainer) {
        trainer.addCoins(STARTING_COINS);
    }

    public void validateAddingAtAll() {
        loginService.validateUserLogged();
        User loggedUser = loginService.getLoggedUser();
        if (trainerRepository.findTrainerByUser(loggedUser).isPresent()) {
            throw new TrainerServiceException("Stworzyłeś już trenera :) Bierz się za łapanie!");
        }
    }

    private Trainer createTrainer(TrainerDTO trainerDTO) {
        return new Trainer(trainerDTO.getName(),
                Trainer.Sex.valueOf(trainerDTO.getSex().toUpperCase()),
                trainerDTO.getType());
    }

    public Trainer getLoggedTrainer() {
        User loggedUser = loginService.getLoggedUser();
        validateHasTrainer();
        return trainerRepository
                .findTrainerByUser(loggedUser)
                .orElseThrow();
    }

    public void validateHasTrainer() {
        loginService.validateUserLogged();
        User loggedUser = loginService.getLoggedUser();
        if (trainerRepository
                .findTrainerByUser(loggedUser)
                .isEmpty()) {
            throw new TrainerServiceException("Najpierw musisz stworzyć trenera");
        }

    }

    public void saveLoggedUserTrainer(Trainer trainer) {
       trainerRepository.saveTrainerForUser(trainer,loginService.getLoggedUser());
    }
}
