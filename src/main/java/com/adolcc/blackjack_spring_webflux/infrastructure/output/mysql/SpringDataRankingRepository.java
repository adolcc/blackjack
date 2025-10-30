package com.adolcc.blackjack_spring_webflux.infrastructure.output.mysql;

import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SpringDataRankingRepository extends ReactiveCrudRepository<Player, String> {
    Flux<Player> findAllByOrderByBalanceDesc();
}