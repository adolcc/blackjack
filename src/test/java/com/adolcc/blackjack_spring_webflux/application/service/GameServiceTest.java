package com.adolcc.blackjack_spring_webflux.application.service;

import com.adolcc.blackjack_spring_webflux.application.dto.request.CreateGameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import com.adolcc.blackjack_spring_webflux.domain.model.Game;
import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import com.adolcc.blackjack_spring_webflux.domain.port.output.GameRepository;
import com.adolcc.blackjack_spring_webflux.domain.port.output.RankingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    private GameRepository gameRepository;
    private RankingRepository rankingRepository;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        rankingRepository = mock(RankingRepository.class);
        gameService = new GameService(gameRepository, rankingRepository);
    }

    @Test
    void shouldCreateGameSuccessfully() {
        CreateGameRequest request = new CreateGameRequest("Alice", 1000.0);
        Game mockGame = new Game(new Player("Alice", 1000.0));
        mockGame.setId("dummy-id");

        when(gameRepository.save(any(Game.class))).thenReturn(Mono.just(mockGame));

        Mono<GameResponse> responseMono = gameService.createGame(request);
        GameResponse response = responseMono.block();

        assertNotNull(response);
        assertEquals("Alice", response.getPlayerName());
        assertEquals(1000.0, response.getPlayerBalance());
        assertEquals("dummy-id", response.getGameId());
    }
}