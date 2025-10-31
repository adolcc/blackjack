package com.adolcc.blackjack_spring_webflux.infrastructure.output.mongodb;

import com.adolcc.blackjack_spring_webflux.domain.model.Game;
import com.adolcc.blackjack_spring_webflux.domain.port.output.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoGameRepository implements GameRepository {
    private final SpringDataMongoGameRepository repo;

    @Autowired
    public MongoGameRepository(SpringDataMongoGameRepository repo) {
        this.repo = repo;
    }

    @Override
    public Mono<Game> save(Game game) {
        return repo.save(game);
    }

    @Override
    public Mono<Game> findById(String gameId) {
        return repo.findById(gameId);
    }

    @Override
    public Flux<Game> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Void> deleteById(String gameId) {
        return repo.deleteById(gameId);
    }
}