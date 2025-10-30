package com.adolcc.blackjack_spring_webflux.application.controller;

import com.adolcc.blackjack_spring_webflux.application.dto.request.CreateGameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.request.PlaceBetRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import com.adolcc.blackjack_spring_webflux.application.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public Mono<GameResponse> createGame(@Valid @RequestBody CreateGameRequest request) {
        return gameService.createGame(request);
    }

    @PostMapping("/{gameId}/bet")
    public Mono<GameResponse> placeBet(@PathVariable String gameId, @Valid @RequestBody PlaceBetRequest request) {
        return gameService.placeBet(gameId, request);
    }

    @PostMapping("/{gameId}/start")
    public Mono<GameResponse> startGame(@PathVariable String gameId) {
        return gameService.startGame(gameId);
    }

    @PostMapping("/{gameId}/hit")
    public Mono<GameResponse> playerHit(@PathVariable String gameId) {
        return gameService.playerHit(gameId);
    }

    @PostMapping("/{gameId}/stand")
    public Mono<GameResponse> playerStand(@PathVariable String gameId) {
        return gameService.playerStand(gameId);
    }

    @GetMapping("/{gameId}")
    public Mono<GameResponse> getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }
}