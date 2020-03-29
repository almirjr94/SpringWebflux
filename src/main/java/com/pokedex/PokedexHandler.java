package com.pokedex;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.pokedex.document.Pokedex;
import com.pokedex.services.PokedexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

//@Component
@RequiredArgsConstructor
public class PokedexHandler {

  private final PokedexService service;

  public Mono<ServerResponse> findAll(ServerRequest request){
    return ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.findAll(), Pokedex.class);
  }

  public Mono<ServerResponse> findById(ServerRequest request){
    String id = request.pathVariable("id");
    return ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.findById(id), Pokedex.class);
  }

  public Mono<ServerResponse> save(ServerRequest request){
    final Mono<Pokedex> pokedex = request.bodyToMono(Pokedex.class);
    return ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(fromPublisher(pokedex.flatMap(service::save),Pokedex.class));
  }

}
