package com.adolcc.blackjack_spring_webflux.application.controller;

import com.adolcc.blackjack_spring_webflux.application.dto.request.ChangePlayerNameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.request.CreateGameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import com.adolcc.blackjack_spring_webflux.application.dto.response.PlayerResponse;
import com.adolcc.blackjack_spring_webflux.application.dto.response.RankingResponse;
import com.adolcc.blackjack_spring_webflux.application.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.adolcc.blackjack_spring_webflux.application.dto.request.PlayRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    public GameControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateGameSuccessfully() {
        CreateGameRequest request = new CreateGameRequest("Alice", 1000.0);
        GameResponse mockResponse = GameResponse.builder()
                .gameId("dummy-id")
                .playerName("Alice")
                .playerBalance(1000.0)
                .build();

        when(gameService.createGame(request)).thenReturn(Mono.just(mockResponse));

        Mono<ResponseEntity<GameResponse>> responseMono = gameController.createGame(request);

        ResponseEntity<GameResponse> response = responseMono.block();

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertEquals("Alice", response.getBody().getPlayerName());
        assertEquals(1000.0, response.getBody().getPlayerBalance());
    }

    @Test
    void shouldGetGameSuccessfully() {
        String gameId = "dummy-id";
        GameResponse mockResponse = GameResponse.builder()
                .gameId(gameId)
                .playerName("Bob")
                .playerBalance(500.0)
                .build();

        when(gameService.getGame(gameId)).thenReturn(Mono.just(mockResponse));

        Mono<ResponseEntity<GameResponse>> responseMono = gameController.getGame(gameId);
        ResponseEntity<GameResponse> response = responseMono.block();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Bob", response.getBody().getPlayerName());
        assertEquals(500.0, response.getBody().getPlayerBalance());
    }

    @Test
    void shouldPlaySuccessfully() {
        String gameId = "dummy-id";
        PlayRequest playRequest = new PlayRequest("hit", 100.0);
        GameResponse mockResponse = GameResponse.builder()
                .gameId(gameId)
                .playerName("Carol")
                .playerBalance(900.0)
                .build();

        when(gameService.play(gameId, playRequest)).thenReturn(Mono.just(mockResponse));

        Mono<ResponseEntity<GameResponse>> responseMono = gameController.play(gameId, playRequest);
        ResponseEntity<GameResponse> response = responseMono.block();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Carol", response.getBody().getPlayerName());
        assertEquals(900.0, response.getBody().getPlayerBalance());
    }

    @Test
    void shouldDeleteGameSuccessfully() {
        String gameId = "dummy-id";
        when(gameService.deleteGame(gameId)).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> responseMono = gameController.deleteGame(gameId);
        ResponseEntity<Void> response = responseMono.block();

        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void shouldGetRankingSuccessfully() {
        RankingResponse ranking1 = new RankingResponse("1", "Alice", 1, 1000.0);
        RankingResponse ranking2 = new RankingResponse("2", "Bob", 2, 800.0);

        when(gameService.getRanking()).thenReturn(Flux.fromIterable(List.of(ranking1, ranking2)));

        List<RankingResponse> rankingList = gameController.getRanking().collectList().block();

        assertNotNull(rankingList);
        assertEquals(2, rankingList.size());
        assertEquals("Alice", rankingList.get(0).getPlayerName());
        assertEquals("Bob", rankingList.get(1).getPlayerName());
    }

    @Test
    void shouldChangePlayerNameSuccessfully() {
        String playerId = "1";
        ChangePlayerNameRequest request = new ChangePlayerNameRequest("NewName");
        PlayerResponse mockResponse = new PlayerResponse(playerId, "NewName", 1000.0);

        when(gameService.changePlayerName(playerId, request)).thenReturn(Mono.just(mockResponse));

        Mono<ResponseEntity<PlayerResponse>> responseMono = gameController.changePlayerName(playerId, request);
        ResponseEntity<PlayerResponse> response = responseMono.block();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("NewName", response.getBody().getPlayerName());
        assertEquals(1000.0, response.getBody().getBalance());
    }
}