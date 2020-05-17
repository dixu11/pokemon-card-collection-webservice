package com.dixu.PokemonCardsService.service.home;

import com.dixu.PokemonCardsService.dto.HomeDTO;
import com.dixu.PokemonCardsService.service.login.LoginService;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private LoginService loginService;

    public HomeService(LoginService loginService) {
        this.loginService = loginService;
    }

    public HomeDTO getLoginStatus(){
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setMail("Niezalogowany");

        if (loginService.isUserLogged()) {
            homeDTO.setLogged(true);
            homeDTO.setMail(loginService.getLoggedUserMail());
        }

        return homeDTO;
    }


}
