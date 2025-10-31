package com.adolcc.blackjack_spring_webflux.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.adolcc.blackjack_spring_webflux.infrastructure.output.mysql")
public class R2dbcConfig {
}