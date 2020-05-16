package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepositoryDb extends JpaRepository<Card,String> {
}
