package com.dixu.PokemonCardsService.service.profile;

import com.dixu.PokemonCardsService.dto.ProfileStatsDTO;
import com.dixu.PokemonCardsService.model.Trainer;
import com.dixu.PokemonCardsService.model.TrainerStats;
import com.dixu.PokemonCardsService.service.trainer.TrainerService;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private TrainerService trainerService;

    public ProfileService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public ProfileStatsDTO getProfileData() {
        Trainer trainer = trainerService.getLoggedTrainer();
        TrainerStats trainerStats = new TrainerStats(trainer);
        return ProfileStatsDTO.builder()
                .cardsCount(trainerStats.getCardsCount())
                .coins(trainerStats.getCoinsCount())
                .commonCount(trainerStats.getCardsOfRarity("common"))
                .rareCount(trainerStats.countNotOfRarity("common"))
                .name(trainerStats.getName())
                .type(trainerStats.getTrainerType())
                .build();
    }
}
