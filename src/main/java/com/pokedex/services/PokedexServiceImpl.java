package com.pokedex.services;

import com.pokedex.document.Pokedex;
import com.pokedex.repository.PokedexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PokedexServiceImpl implements PokedexService {

  private final PokedexRepository pokedexRepository;

  @Override
  public Flux<Pokedex> findAll() {
    return pokedexRepository.findAll();
  }

  @Override
  public Mono<Pokedex> findById(String id) {
    return pokedexRepository.findById(id);
  }

  @Override
  public Mono<Pokedex> save(Pokedex pokedex) {
    return pokedexRepository.save(pokedex);
  }
}
