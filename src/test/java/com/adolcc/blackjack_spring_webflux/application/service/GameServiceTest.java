package com.adolcc.blackjack_spring_webflux.application.service;

import com.adolcc.blackjack_spring_webflux.application.dto.request.CreateGameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private final GameService gameService = new GameService();

    @Test
    void shouldCreateGameSuccessfully() {
        CreateGameRequest request = new CreateGameRequest("Alice", 1000.0);

        Mono<GameResponse> responseMono = gameService.createGame(request);
        GameResponse response = responseMono.block();

        assertNotNull(response);
        assertEquals("Alice", response.getPlayerName());
        assertEquals(1000.0, response.getPlayerBalance());
        assertNotNull(response.getGameId());
    }
}