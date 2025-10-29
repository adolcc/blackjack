package com.adolcc.blackjack_spring_webflux.domain.model;

import lombok.Getter;

@Getter
public class Player {
    private final String name;
    private final double balance;
    private final Hand hand = new Hand();

    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
}
