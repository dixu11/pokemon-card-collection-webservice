package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserRepositoryDb extends CrudRepository<User,String>,UserRepository {
}
