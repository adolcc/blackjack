package com.adolcc.blackjack_spring_webflux.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Game {
    private final Player player;
    private final Player dealer;
    private final Deck deck;
    @Setter
    private GameState state;
    private double betAmount;

    public Game(Player player) {
        this.player = player;
        this.dealer = new Player("Dealer", 0);
        this.deck = new Deck();
        this.state = GameState.BETTING;
        this.betAmount = 0.0;
    }

    public void placeBet(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Bet amount must be greater than zero");
        }

        if (amount > player.getBalance()) {
            throw new IllegalArgumentException("Bet amount cannot exceed player balance");
        }

        this.betAmount = amount;
        player.deductBalance(amount);
    }

    public void start() {
        deck.shuffle();
        player.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        this.state = GameState.PLAYER_TURN;
    }

    public void playerHit() {
        player.getHand().addCard(deck.drawCard());

        if (player.getHand().isBust()) {
            this.state = GameState.FINISHED;
        }
    }

    public void dealerPlay() {
        this.state = GameState.DEALER_TURN;

        while (dealer.getHand().getTotal() < 17) {
            dealer.getHand().addCard(deck.drawCard());
        }

        this.state = GameState.FINISHED;
    }

    public void playerStand() {
        this.state = GameState.DEALER_TURN;
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

    public void settleGame() {
        String result = determineWinner();

        switch (result) {
            case "PLAYER_WINS":
                if (player.getHand().isBlackjack()) {
                    player.addBalance(betAmount * 2.5);
                } else {
                    player.addBalance(betAmount * 2.0);
                }
                break;
            case "TIE":
                player.addBalance(betAmount);
                break;
            case "DEALER_WINS":
                break;
        }
    }
}