package com.adolcc.blackjack_spring_webflux.infrastructure.input.rest;

import com.adolcc.blackjack_spring_webflux.application.dto.request.CreateGameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.request.PlayRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.request.ChangePlayerNameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import com.adolcc.blackjack_spring_webflux.application.dto.response.RankingResponse;
import com.adolcc.blackjack_spring_webflux.application.dto.response.PlayerResponse;
import com.adolcc.blackjack_spring_webflux.application.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/game/new")
    public Mono<ResponseEntity<GameResponse>> createGame(@Valid @RequestBody CreateGameRequest request) {
        return gameService.createGame(request)
                .map(gameResponse -> ResponseEntity.status(HttpStatus.CREATED).body(gameResponse));
    }

    @GetMapping("/game/{id}")
    public Mono<ResponseEntity<GameResponse>> getGame(@PathVariable String id) {
        return gameService.getGame(id)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/game/{id}/play")
    public Mono<ResponseEntity<GameResponse>> play(@PathVariable String id, @Valid @RequestBody PlayRequest request) {
        return gameService.play(id, request)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/game/{id}/delete")
    public Mono<ResponseEntity<Void>> deleteGame(@PathVariable String id) {
        return gameService.deleteGame(id)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/ranking")
    public Flux<RankingResponse> getRanking() {
        return gameService.getRanking();
    }

    @PutMapping("/player/{playerId}")
    public Mono<ResponseEntity<PlayerResponse>> changePlayerName(@PathVariable String playerId, @Valid @RequestBody ChangePlayerNameRequest request) {
        return gameService.changePlayerName(playerId, request)
                .map(ResponseEntity::ok);
    }
}