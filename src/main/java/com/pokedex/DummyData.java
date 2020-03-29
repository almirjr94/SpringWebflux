package com.pokedex;

import com.pokedex.document.Pokedex;
import com.pokedex.repository.PokedexRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class DummyData implements CommandLineRunner {

  private final PokedexRepository pokedexRepository;

  @Override
  public void run(String... args) throws Exception {
    pokedexRepository.deleteAll().
        thenMany(Flux.just("Bulbasaur", "Venusaur", "Charmander", "Charmaleon"))
        .map(Pokedex::new)
        .flatMap(pokedexRepository::save).subscribe(System.out::println);
  }
}
