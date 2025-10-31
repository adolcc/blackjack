package com.adolcc.blackjack_spring_webflux.domain.port.input;

import com.adolcc.blackjack_spring_webflux.application.dto.request.ChangePlayerNameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.PlayerResponse;
import reactor.core.publisher.Mono;

public interface ChangePlayerNameUseCase {
    Mono<PlayerResponse> changePlayerName(String playerId, ChangePlayerNameRequest request);
}