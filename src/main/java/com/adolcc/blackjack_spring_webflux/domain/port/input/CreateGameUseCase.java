package com.adolcc.blackjack_spring_webflux.domain.port.input;

import com.adolcc.blackjack_spring_webflux.application.dto.request.CreateGameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import reactor.core.publisher.Mono;

public interface CreateGameUseCase {
    Mono<GameResponse> createGame(CreateGameRequest request);
}