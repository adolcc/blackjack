package com.adolcc.blackjack_spring_webflux.application.service;

import com.adolcc.blackjack_spring_webflux.domain.port.input.GetRankingUseCase;
import com.adolcc.blackjack_spring_webflux.application.dto.response.RankingResponse;
import com.adolcc.blackjack_spring_webflux.domain.port.output.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RankingService implements GetRankingUseCase {

    private final RankingRepository rankingRepository;

    @Override
    public Flux<RankingResponse> getRanking() {
        return rankingRepository.findAllPlayersOrderedByBalance()
                .map(player -> new RankingResponse(
                        player.getId(),
                        player.getName(),
                        player.getGamesPlayed(),
                        player.getBalance()
                ));
    }
}