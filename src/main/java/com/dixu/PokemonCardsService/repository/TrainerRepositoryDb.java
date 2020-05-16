package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.model.Trainer;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface TrainerRepositoryDb extends CrudRepository<Trainer,String>, TrainerRepository{
}
