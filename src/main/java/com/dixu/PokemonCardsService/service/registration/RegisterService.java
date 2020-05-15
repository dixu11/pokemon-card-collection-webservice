package com.dixu.PokemonCardsService.service.registration;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class RegisterService {

    private UserRepository repository;
    @Value("${testmode}")
    private boolean testMode;

    @PostConstruct
    private void addStartingData() {
        if (testMode) {
            register(new UserDTO("d@d.pl","12345"));
        }
    }

    @Autowired
    public RegisterService(UserRepository repository) {
        this.repository = repository;
    }

    public void register(UserDTO userDTO) {
        User user = new User(userDTO.getMail(),userDTO.getPassword());
        validateIfAlreadyRegistered(user);
        repository.saveUser(new User(userDTO.getMail(),userDTO.getPassword()));
    }

    private void validateIfAlreadyRegistered(User user) {
        Optional<User> sameUser = repository.findByMail(user.getMail());
        if (sameUser.isPresent()) {
            throw new RegisterServiceException("Wybrałeś maila, którego mamy już w bazie! Spróbuj się zalogować.");
        }
    }

}
