package com.adolcc.blackjack_spring_webflux.domain.port.input;

import com.adolcc.blackjack_spring_webflux.application.dto.request.PlayRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import reactor.core.publisher.Mono;

public interface PlayGameUseCase {
    Mono<GameResponse> play(String gameId, PlayRequest request);
}
