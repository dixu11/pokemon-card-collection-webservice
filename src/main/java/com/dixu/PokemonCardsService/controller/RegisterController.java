package com.dixu.PokemonCardsService.controller;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.service.registration.RegisterService;
import com.dixu.PokemonCardsService.service.registration.RegisterServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

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

    //adnotacja dodaje otrzymanego usera od razu do modelu
    @PostMapping
    String registerNewUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult errors) {
        if (errors.hasErrors()) {
            return "register-form";
        }

        try {
            registerService.register(user);
        } catch (RegisterServiceException e) {
          errors.addError(new FieldError("user","mail",e.getMessage()));
            return "register-form";
        }

        return "redirect:login";
    }


}
