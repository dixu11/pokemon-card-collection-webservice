package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.service.RegisterService;
import com.dixu.PokemonCardsService.service.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
class RegisterController {

    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    String getRegisterPage(){
        return "register-form";
    }



    @PostMapping
    String registerNewUser(@RequestParam String mail, @RequestParam String pass, Model model) {
        try {
            registerService.register(new UserDTO(mail, pass));
        } catch (UserServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "register-failed";
        }
        return "register-success";
    }


}
