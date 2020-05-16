package com.dixu.PokemonCardsService.repository;

import com.dixu.PokemonCardsService.model.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetsRepositoryDb extends JpaRepository<CardSet, String> {

}
