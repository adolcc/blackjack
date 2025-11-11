package com.adolcc.blackjack_spring_webflux.infrastructure.input.rest.controller;

import com.adolcc.blackjack_spring_webflux.application.dto.request.ChangePlayerNameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.PlayerResponse;
import com.adolcc.blackjack_spring_webflux.application.service.PlayerService;
import com.adolcc.blackjack_spring_webflux.infrastructure.input.rest.GameController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private GameController gameController;

    public PlayerControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldChangePlayerNameSuccessfully() {
        String playerId = "1";
        ChangePlayerNameRequest request = new ChangePlayerNameRequest("NewName");
        PlayerResponse mockResponse = new PlayerResponse(playerId, "NewName", 1000.0, 5);

        when(playerService.changePlayerName(playerId, request)).thenReturn(Mono.just(mockResponse));

        Mono<ResponseEntity<PlayerResponse>> responseMono = gameController.changePlayerName(playerId, request);
        ResponseEntity<PlayerResponse> response = responseMono.block();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("NewName", response.getBody().getPlayerName());
        assertEquals(1000.0, response.getBody().getBalance());
        assertEquals(5, response.getBody().getGamesPlayed());
    }
}