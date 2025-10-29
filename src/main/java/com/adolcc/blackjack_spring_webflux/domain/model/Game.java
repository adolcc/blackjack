package com.adolcc.blackjack_spring_webflux.domain.model;

import lombok.Getter;

@Getter
public class Game {
    private final Player player;
    private final Player dealer;
    private final Deck deck;

    public Game(Player player) {
        this.player = player;
        this.dealer = new Player("Dealer", 0);
        this.deck = new Deck();
    }

    public void start() {
        deck.shuffle();
        player.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
    }
    public void playerHit() {
        player.getHand().addCard(deck.drawCard());
    }
}