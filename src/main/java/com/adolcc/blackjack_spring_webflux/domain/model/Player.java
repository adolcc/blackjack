package com.adolcc.blackjack_spring_webflux.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "players")
@Data
public class Player {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    private double balance;
    private int gamesPlayed;

    @Transient
    private Hand hand;

    public Player() {
        this.hand = new Hand();
    }

    public Player(String id, String name, double balance, int gamesPlayed) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.gamesPlayed = gamesPlayed;
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
