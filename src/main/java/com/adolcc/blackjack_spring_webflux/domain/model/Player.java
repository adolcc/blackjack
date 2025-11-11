package com.adolcc.blackjack_spring_webflux.domain.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("players")
public class Player {
    @Id
    private String id;
    private String name;
    private Double balance;
    @Column("games_played")
    private Integer gamesPlayed;

    @Transient
    private Hand hand;

    public Player() {
        this.hand = new Hand();
        this.id = UUID.randomUUID().toString();
    }

    public Player(String id, String name, double balance, int gamesPlayed) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.gamesPlayed = gamesPlayed;
        this.hand = new Hand();
    }

    public Player(String name, double balance) {
        this.id = UUID.randomUUID().toString();
        this.id = null;
        this.name = name;
        this.balance = balance;
        this.gamesPlayed = 0;
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
