package com.adolcc.blackjack_spring_webflux.domain.port.input;

import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import reactor.core.publisher.Mono;

public interface GetGameUseCase {
    Mono<GameResponse> getGame(String gameId);
}