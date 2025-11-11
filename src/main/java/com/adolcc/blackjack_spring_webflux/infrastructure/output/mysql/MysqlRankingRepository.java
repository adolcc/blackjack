package com.adolcc.blackjack_spring_webflux.infrastructure.output.mysql;

import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import com.adolcc.blackjack_spring_webflux.domain.port.output.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MysqlRankingRepository implements RankingRepository {
    private final SpringDataRankingRepository repo;

    @Override
    public Flux<Player> findAllPlayersOrderedByBalance() {
        return repo.findAllPlayersOrderedByBalance();
    }

    @Override
    public Mono<Player> save(Player player) {
        return repo.save(player);
    }
}