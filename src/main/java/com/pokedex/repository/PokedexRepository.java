package com.pokedex.repository;

import com.pokedex.document.Pokedex;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository<Pokedex, String> {

}
