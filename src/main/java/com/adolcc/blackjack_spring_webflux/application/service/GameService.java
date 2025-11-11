package com.adolcc.blackjack_spring_webflux.application.service;

import com.adolcc.blackjack_spring_webflux.domain.port.input.*;
import com.adolcc.blackjack_spring_webflux.application.dto.request.*;
import com.adolcc.blackjack_spring_webflux.application.dto.response.*;
import com.adolcc.blackjack_spring_webflux.domain.port.output.GameRepository;
import com.adolcc.blackjack_spring_webflux.domain.port.output.RankingRepository;
import com.adolcc.blackjack_spring_webflux.domain.model.Game;
import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameService implements
        CreateGameUseCase,
        PlayGameUseCase,
        GetGameUseCase,
        DeleteGameUseCase {

    private final GameRepository gameRepository;
    private final RankingRepository rankingRepository;

    @Override
    public Mono<GameResponse> createGame(CreateGameRequest request) {
        Player player = new Player(request.getPlayerName(), request.getInitialBalance());

        return rankingRepository.save(player)
                .flatMap(savedPlayer -> {
                    Game game = new Game(savedPlayer);
                    return gameRepository.save(game)
                            .map(savedGame -> GameResponse.fromGame(savedGame, savedGame.getId()));
                });
    }

    @Override
    public Mono<GameResponse> play(String gameId, PlayRequest request) {
        return gameRepository.findById(gameId)
                .flatMap(game -> {
                    switch (request.getAction().toLowerCase()) {
                        case "bet" -> {
                            game.placeBet(request.getAmount());
                            return gameRepository.save(game)
                                    .map(g -> GameResponse.fromGame(g, gameId));
                        }
                        case "hit" -> {
                            game.playerHit();
                            return gameRepository.save(game)
                                    .map(g -> GameResponse.fromGame(g, gameId));
                        }
                        case "stand" -> {
                            game.playerStand();
                            game.dealerPlay();
                            game.settleGame();
                            Player player = game.getPlayer();
                            player.setGamesPlayed(player.getGamesPlayed() + 1);
                            return rankingRepository.save(player)
                                    .flatMap(savedPlayer ->
                                            gameRepository.save(game)
                                                    .map(g -> GameResponse.fromGameWithWinner(g, gameId, game.determineWinner()))
                                    );
                        }
                        default -> {
                            return Mono.error(new IllegalArgumentException("Unknown action: " + request.getAction()));
                        }
                    }
                });
    }

    @Override
    public Mono<GameResponse> getGame(String gameId) {
        return gameRepository.findById(gameId)
                .map(game -> GameResponse.fromGame(game, gameId));
    }

    @Override
    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.deleteById(gameId);
    }
}