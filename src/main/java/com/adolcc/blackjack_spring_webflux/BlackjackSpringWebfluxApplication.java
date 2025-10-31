package com.adolcc.blackjack_spring_webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "com.adolcc.blackjack_spring_webflux.infrastructure.output.mysql")
@EnableReactiveMongoRepositories(basePackages = "com.adolcc.blackjack_spring_webflux.infrastructure.output.mongodb")
public class BlackjackSpringWebfluxApplication {

	public static void main(String[] args) {
        SpringApplication.run(BlackjackSpringWebfluxApplication.class, args);
	}
}
