package com.adolcc.blackjack_spring_webflux.domain.port.input;

import reactor.core.publisher.Mono;

public interface DeleteGameUseCase {
    Mono<Void> deleteGame(String gameId);
}