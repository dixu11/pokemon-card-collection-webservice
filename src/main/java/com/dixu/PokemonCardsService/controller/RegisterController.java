package com.dixu.PokemonCardsService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
class RegisterController {
    @GetMapping
    String getRegisterPage(){
        return "register-form";
    }

    @PostMapping
    String registerNewUser(@RequestParam String mail, @RequestParam String pass) {
        System.out.println(mail);
        System.out.println(pass);
        return "index";
    }

}
