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
    public void dealerPlay() {
        while (dealer.getHand().getTotal() < 17) {
            dealer.getHand().addCard(deck.drawCard());
        }
    }

    public String determineWinner() {
        Hand playerHand = player.getHand();
        Hand dealerHand = dealer.getHand();
        if (playerHand.isBust()) return "DEALER_WINS";
        if (dealerHand.isBust()) return "PLAYER_WINS";
        int comparison = Integer.compare(playerHand.getTotal(), dealerHand.getTotal());
        if (comparison > 0) return "PLAYER_WINS";
        if (comparison < 0) return "DEALER_WINS";
        return "TIE";
    }
}