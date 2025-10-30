package com.adolcc.blackjack_spring_webflux.application.service;

import com.adolcc.blackjack_spring_webflux.application.dto.request.CreateGameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.request.PlaceBetRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.request.PlayRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.request.ChangePlayerNameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.GameResponse;
import com.adolcc.blackjack_spring_webflux.application.dto.response.RankingResponse;
import com.adolcc.blackjack_spring_webflux.application.dto.response.PlayerResponse;
import com.adolcc.blackjack_spring_webflux.domain.model.Game;
import com.adolcc.blackjack_spring_webflux.domain.model.GameState;
import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameService {

    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public Mono<GameResponse> createGame(CreateGameRequest request) {
        return Mono.fromCallable(() -> {
            String gameId = UUID.randomUUID().toString();
            Player player = new Player(request.getPlayerName(), request.getInitialBalance());
            Game game = new Game(player);
            games.put(gameId, game);
            log.info("Game created with ID: {}", gameId);
            return GameResponse.fromGame(game, gameId);
        });
    }

    public Mono<GameResponse> placeBet(String gameId, PlaceBetRequest request) {
        return Mono.fromCallable(() -> {
            Game game = getGameOrThrow(gameId);
            if (game.getState() != GameState.BETTING) {
                throw new IllegalStateException("Cannot place bet in current game state");
            }
            game.placeBet(request.getAmount());
            log.info("Bet placed: {} for game: {}", request.getAmount(), gameId);
            return GameResponse.fromGame(game, gameId);
        });
    }

    public Mono<GameResponse> startGame(String gameId) {
        return Mono.fromCallable(() -> {
            Game game = getGameOrThrow(gameId);
            if (game.getBetAmount() <= 0) {
                throw new IllegalStateException("Must place bet before starting game");
            }
            game.start();
            log.info("Game started: {}", gameId);
            return GameResponse.fromGame(game, gameId);
        });
    }

    public Mono<GameResponse> playerHit(String gameId) {
        return Mono.fromCallable(() -> {
            Game game = getGameOrThrow(gameId);
            if (game.getState() != GameState.PLAYER_TURN) {
                throw new IllegalStateException("Cannot hit in current game state");
            }
            game.playerHit();
            if (game.getState() == GameState.FINISHED) {
                game.settleGame();
                String winner = game.determineWinner();
                log.info("Player busted. Game finished: {}", gameId);
                return GameResponse.fromGameWithWinner(game, gameId, winner);
            }
            log.info("Player hit in game: {}", gameId);
            return GameResponse.fromGame(game, gameId);
        });
    }

    public Mono<GameResponse> playerStand(String gameId) {
        return Mono.fromCallable(() -> {
            Game game = getGameOrThrow(gameId);
            if (game.getState() != GameState.PLAYER_TURN) {
                throw new IllegalStateException("Cannot stand in current game state");
            }
            game.playerStand();
            game.dealerPlay();
            game.settleGame();
            String winner = game.determineWinner();
            log.info("Player stood. Game finished: {}", gameId);
            return GameResponse.fromGameWithWinner(game, gameId, winner);
        });
    }

    public Mono<GameResponse> getGame(String gameId) {
        return Mono.fromCallable(() -> {
            Game game = getGameOrThrow(gameId);
            return GameResponse.fromGame(game, gameId);
        });
    }

    public Mono<GameResponse> play(String id, PlayRequest request) {
        if ("hit".equalsIgnoreCase(request.getAction())) {
            return playerHit(id);
        } else if ("stand".equalsIgnoreCase(request.getAction())) {
            return playerStand(id);
        } else if ("bet".equalsIgnoreCase(request.getAction())) {
            PlaceBetRequest betRequest = new PlaceBetRequest(request.getAmount());
            return placeBet(id, betRequest);
        }
        return Mono.error(new IllegalArgumentException("Unknown action: " + request.getAction()));
    }

    public Mono<Void> deleteGame(String id) {
        return Mono.fromRunnable(() -> games.remove(id));
    }

    public Flux<RankingResponse> getRanking() {
        return Flux.fromIterable(
                games.values().stream()
                        .map(Game::getPlayer)
                        .distinct()
                        .sorted((a, b) -> Double.compare(b.getBalance(), a.getBalance()))
                        .map(player -> new RankingResponse(
                                player.getName(),
                                player.getName(),
                                0,
                                player.getBalance()
                        ))
                        .collect(Collectors.toList())
        );
    }

    public Mono<PlayerResponse> changePlayerName(String playerId, ChangePlayerNameRequest request) {
        for (Game game : games.values()) {
            Player player = game.getPlayer();
            if (player.getName().equals(playerId)) {
                player.setName(request.getNewName());
                return Mono.just(new PlayerResponse(playerId, request.getNewName(), player.getBalance()));
            }
        }
        return Mono.error(new IllegalArgumentException("Player not found: " + playerId));
    }

    private Game getGameOrThrow(String gameId) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found: " + gameId);
        }
        return game;
    }
}