package com.adolcc.blackjack_spring_webflux.infrastructure.output.mongodb;

import com.adolcc.blackjack_spring_webflux.domain.model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SpringDataMongoGameRepository extends ReactiveMongoRepository<Game, String> {}