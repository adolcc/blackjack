package com.adolcc.blackjack_spring_webflux.application.service;

import com.adolcc.blackjack_spring_webflux.domain.port.input.ChangePlayerNameUseCase;
import com.adolcc.blackjack_spring_webflux.application.dto.request.ChangePlayerNameRequest;
import com.adolcc.blackjack_spring_webflux.application.dto.response.PlayerResponse;
import com.adolcc.blackjack_spring_webflux.domain.port.output.RankingRepository;
import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayerService implements ChangePlayerNameUseCase {

    private final RankingRepository rankingRepository;

    @Override
    public Mono<PlayerResponse> changePlayerName(String playerId, ChangePlayerNameRequest request) {
        return rankingRepository.findAllPlayersOrderedByBalance()
                .filter(player -> player.getId().equals(playerId))
                .next()
                .flatMap(player -> {
                    player.setName(request.getNewName());
                    return rankingRepository.save(player)
                            .map(savedPlayer -> new PlayerResponse(
                                    savedPlayer.getId(),
                                    savedPlayer.getName(),
                                    savedPlayer.getBalance(),
                                    savedPlayer.getGamesPlayed()
                            ));
                });
    }
}