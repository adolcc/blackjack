package com.adolcc.blackjack_spring_webflux.infrastructure.output.mongodb;

import com.adolcc.blackjack_spring_webflux.domain.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MongoGameRepositoryTest {

    private SpringDataMongoGameRepository springRepo;
    private MongoGameRepository mongoGameRepository;

    @BeforeEach
    void setUp() {
        springRepo = mock(SpringDataMongoGameRepository.class);
        mongoGameRepository = new MongoGameRepository(springRepo);
    }

    @Test
    void testSaveDelegatesToSpringRepo() {
        Game game = new Game();
        when(springRepo.save(game)).thenReturn(Mono.just(game));

        Mono<Game> result = mongoGameRepository.save(game);

        assertEquals(game, result.block());
        verify(springRepo).save(game);
    }

    @Test
    void testFindByIdDelegatesToSpringRepo() {
        Game game = new Game();
        game.setId("test-id");
        when(springRepo.findById("test-id")).thenReturn(Mono.just(game));

        Mono<Game> result = mongoGameRepository.findById("test-id");

        assertEquals(game, result.block());
        verify(springRepo).findById("test-id");
    }

    @Test
    void testFindAllDelegatesToSpringRepo() {
        Game game1 = new Game();
        Game game2 = new Game();
        when(springRepo.findAll()).thenReturn(Flux.just(game1, game2));

        Flux<Game> result = mongoGameRepository.findAll();

        assertEquals(2, result.collectList().block().size());
        verify(springRepo).findAll();
    }

    @Test
    void testDeleteByIdDelegatesToSpringRepo() {
        when(springRepo.deleteById("test-id")).thenReturn(Mono.empty());

        Mono<Void> result = mongoGameRepository.deleteById("test-id");

        result.block();
        verify(springRepo).deleteById("test-id");
    }
}