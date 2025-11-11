package com.adolcc.blackjack_spring_webflux.infrastructure.output.mysql;

import com.adolcc.blackjack_spring_webflux.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MysqlRankingRepositoryTest {

    private SpringDataRankingRepository springRepo;
    private MysqlRankingRepository rankingRepository;

    @BeforeEach
    void setUp() {
        springRepo = mock(SpringDataRankingRepository.class);
        rankingRepository = new MysqlRankingRepository(springRepo);
    }

    @Test
    void testFindAllPlayersOrderedByBalanceDelegatesToSpringRepo() {
        Player player1 = new Player("1", "Alice", 100.0, 10);
        Player player2 = new Player("2", "Bob", 150.0, 15);

        when(springRepo.findAllPlayersOrderedByBalance()).thenReturn(Flux.just(player2, player1));

        Flux<Player> result = rankingRepository.findAllPlayersOrderedByBalance();

        assertEquals(2, result.collectList().block().size());
        verify(springRepo).findAllPlayersOrderedByBalance();
    }

    @Test
    void testSaveDelegatesToSpringRepo() {
        Player player = new Player("1", "Alice", 100.0, 10);
        when(springRepo.save(player)).thenReturn(Mono.just(player));

        Mono<Player> result = rankingRepository.save(player);

        assertEquals(player, result.block());
        verify(springRepo).save(player);
    }
}