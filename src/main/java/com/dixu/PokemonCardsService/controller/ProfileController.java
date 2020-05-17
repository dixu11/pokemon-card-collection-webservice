package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.ProfileStatsDTO;
import com.dixu.PokemonCardsService.service.profile.ProfileService;
import com.dixu.PokemonCardsService.service.trainer.TrainerServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String getStatisticsPage(Model model) {
        try {
            ProfileStatsDTO profile = profileService.getProfileData();
            model.addAttribute("profile", profile);
        } catch (TrainerServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "no-access";
        }
        return "profile-view";
    }
}
