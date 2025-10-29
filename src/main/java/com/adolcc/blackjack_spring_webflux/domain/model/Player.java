package com.adolcc.blackjack_spring_webflux.domain.model;

import lombok.Getter;

@Getter
public class Player {
    private final String name;
    private double balance;
    private final Hand hand;

    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.hand = new Hand();
    }

    public void deductBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to deduct cannot be negative");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Cannot deduct more than current balance");
        }

        this.balance -= amount;
    }

    public void addBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }

        this.balance += amount;
    }
}
