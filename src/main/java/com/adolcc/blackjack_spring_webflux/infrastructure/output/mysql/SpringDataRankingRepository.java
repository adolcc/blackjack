package com.adolcc.blackjack_spring_webflux.infrastructure.output.mysql;

import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import org.springframework.data.r2dbc.repository.Query; // Importar la anotación Query
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SpringDataRankingRepository extends R2dbcRepository<Player, String> {

    @Query("SELECT `id`, `name`, `balance`, `games_played` FROM `players` ORDER BY `balance` DESC")
    Flux<Player> findAllPlayersOrderedByBalance();
}