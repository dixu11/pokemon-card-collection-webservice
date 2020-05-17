package com.dixu.PokemonCardsService.service.trainer;

import com.dixu.PokemonCardsService.dto.TrainerDTO;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.TrainerRepository;
import com.dixu.PokemonCardsService.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        trainerRepository.save(trainer);
        return trainer;
    }

    public void addStartingCoins(Trainer trainer) {
        trainer.addCoins(STARTING_COINS);
    }

    public void validateAddingAtAll() {
        String mail = loginService.getLoggedUserMail();
        if (trainerRepository.findTrainerByMail(mail)
                .isPresent()) {
            throw new TrainerServiceException("Stworzyłeś już trenera :) Bierz się za łapanie!");
        }
    }

    private Trainer createTrainer(TrainerDTO trainerDTO) {
        return new Trainer(
                loginService.getLoggedUserMail(),
                trainerDTO.getName(),
                Trainer.Sex.valueOf(trainerDTO.getSex().toUpperCase()),
                trainerDTO.getType());
    }

    public Trainer getLoggedTrainer() {
        validateHasTrainer();
        return trainerRepository
                .findTrainerByMail(loginService.getLoggedUserMail())
                .orElseThrow();
    }

    public void validateHasTrainer() {
        if (trainerRepository
                .findTrainerByMail(loginService.getLoggedUserMail())
                .isEmpty()) {
            throw new TrainerServiceException("Najpierw musisz stworzyć trenera");
        }

    }
}
