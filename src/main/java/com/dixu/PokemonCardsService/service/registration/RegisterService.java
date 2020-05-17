package com.dixu.PokemonCardsService.service.registration;

import com.dixu.PokemonCardsService.dto.UserDTO;
import com.dixu.PokemonCardsService.model.User;
import com.dixu.PokemonCardsService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class RegisterService {

    private UserRepository repository;
    private PasswordEncoder encoder;
    @Value("${testmode}")
    private boolean testMode;

    @Autowired
    public RegisterService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostConstruct
    private void addStartingData() {
        if (testMode) {
            if (repository.findByMail("d@d.pl").isEmpty()) {
                register(new UserDTO("d@d.pl","12345"));
            }
        }
    }

    public void register(UserDTO userDTO) {
        String password = encoder.encode(userDTO.getPassword());
        User user = new User(userDTO.getMail(),password);
        validateIfAlreadyRegistered(user);
        repository.save(user);
    }

    private void validateIfAlreadyRegistered(User user) {
        Optional<User> sameUser = repository.findByMail(user.getMail());
        if (sameUser.isPresent()) {
            throw new RegisterServiceException("Wybrałeś maila, którego mamy już w bazie! Spróbuj się zalogować.");
        }
    }
}
