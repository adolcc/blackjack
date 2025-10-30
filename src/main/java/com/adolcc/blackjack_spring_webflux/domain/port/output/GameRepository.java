package com.adolcc.blackjack_spring_webflux.domain.port.output;

import com.adolcc.blackjack_spring_webflux.domain.model.Game;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface GameRepository {
    Mono<Game> save(Game game);
    Mono<Game> findById(String gameId);
    Flux<Game> findAll();
    Mono<Void> deleteById(String gameId);
}