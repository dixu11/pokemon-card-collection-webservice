package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.TrainerDTO;
import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.service.RegisterService;
import com.dixu.PokemonCardsService.service.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
class RegisterController {

    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    String getRegisterPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "register-form";
    }

    @PostMapping
    String registerNewUser(@ModelAttribute UserDTO user, Model model) {
        System.out.println(user);
        try {
            registerService.register(user);
        } catch (UserServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "register-failed";
        }
        return "register-success";
    }


}
