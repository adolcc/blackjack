package com.adolcc.blackjack_spring_webflux.domain.port.output;

import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import reactor.core.publisher.Flux;

public interface RankingRepository {
    Flux<Player> findAllPlayersOrderedByBalance();
}