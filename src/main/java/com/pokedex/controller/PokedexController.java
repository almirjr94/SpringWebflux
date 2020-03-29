package com.pokedex.controller;

import com.pokedex.document.Pokedex;
import com.pokedex.services.PokedexService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/pokedex")
@RequiredArgsConstructor
public class PokedexController {

  private final PokedexService pokedexService;

  @GetMapping
  public Flux<Pokedex> getAll() {
    return pokedexService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Pokedex> getById(@PathVariable("id") String id) {
    return pokedexService.findById(id);
  }

  @PostMapping
  public Mono<Pokedex> save(@RequestBody Pokedex pokedex) {
    return pokedexService.save(pokedex);
  }

  @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Tuple2<Long, Pokedex>> getPokemonsByEvents() {
    Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
    Flux<Pokedex> events = pokedexService.findAll();
    return Flux.zip(interval, events);
  }

}
