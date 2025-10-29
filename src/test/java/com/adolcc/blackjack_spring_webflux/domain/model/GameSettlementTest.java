package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameSettlementTest {

    @Test
    void shouldPayBlackjackWinnings() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.placeBet(100.0);
        player.getHand().addCard(new Card("A", "HEARTS"));
        player.getHand().addCard(new Card("K", "DIAMONDS"));
        game.getDealer().getHand().addCard(new Card("K", "CLUBS"));
        game.getDealer().getHand().addCard(new Card("Q", "SPADES"));
        game.settleGame();
        assertEquals(1150.0, player.getBalance());
    }

    @Test
    void shouldPayNormalWinnings() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.placeBet(100.0);
        player.getHand().addCard(new Card("K", "HEARTS"));
        player.getHand().addCard(new Card("Q", "DIAMONDS"));
        game.getDealer().getHand().addCard(new Card("K", "CLUBS"));
        game.getDealer().getHand().addCard(new Card("9", "SPADES"));
        game.settleGame();
        assertEquals(1100.0, player.getBalance());
    }

    @Test
    void shouldReturnBetOnTie() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.placeBet(100.0);
        player.getHand().addCard(new Card("K", "HEARTS"));
        player.getHand().addCard(new Card("Q", "DIAMONDS"));
        game.getDealer().getHand().addCard(new Card("K", "CLUBS"));
        game.getDealer().getHand().addCard(new Card("Q", "SPADES"));
        game.settleGame();
        assertEquals(1000.0, player.getBalance());
    }

    @Test
    void shouldNotPayWhenPlayerLoses() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.placeBet(100.0);
        player.getHand().addCard(new Card("K", "HEARTS"));
        player.getHand().addCard(new Card("8", "DIAMONDS"));
        game.getDealer().getHand().addCard(new Card("K", "CLUBS"));
        game.getDealer().getHand().addCard(new Card("Q", "SPADES"));
        game.settleGame();
        assertEquals(900.0, player.getBalance());
    }
}