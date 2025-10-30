package com.adolcc.blackjack_spring_webflux.infrastructure.output.mysql;

import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import com.adolcc.blackjack_spring_webflux.domain.port.output.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class MysqlRankingRepository implements RankingRepository {
    private final SpringDataRankingRepository repo;

    @Autowired
    public MysqlRankingRepository(SpringDataRankingRepository repo) {
        this.repo = repo;
    }

    @Override
    public Flux<Player> findAllPlayersOrderedByBalance() {
        return repo.findAllByOrderByBalanceDesc();
    }
}