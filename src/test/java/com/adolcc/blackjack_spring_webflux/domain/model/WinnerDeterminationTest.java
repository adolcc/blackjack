package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WinnerDeterminationTest {

    @Test
    void shouldPlayerWinWhenDealerBusts() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        player.getHand().addCard(new Card("K", "HEARTS"));
        player.getHand().addCard(new Card("Q", "DIAMONDS"));
        game.getDealer().getHand().addCard(new Card("K", "CLUBS"));
        game.getDealer().getHand().addCard(new Card("Q", "SPADES"));
        game.getDealer().getHand().addCard(new Card("5", "HEARTS"));
        String result = game.determineWinner();
        assertEquals("PLAYER_WINS", result);
    }

    @Test
    void shouldDealerWinWhenPlayerBusts() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        player.getHand().addCard(new Card("K", "HEARTS"));
        player.getHand().addCard(new Card("Q", "DIAMONDS"));
        player.getHand().addCard(new Card("5", "CLUBS"));
        game.getDealer().getHand().addCard(new Card("K", "SPADES"));
        game.getDealer().getHand().addCard(new Card("Q", "CLUBS"));
        String result = game.determineWinner();
        assertEquals("DEALER_WINS", result);
    }

    @Test
    void shouldBeTieWhenBothHaveSameTotal() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        player.getHand().addCard(new Card("K", "HEARTS"));
        player.getHand().addCard(new Card("Q", "DIAMONDS"));
        game.getDealer().getHand().addCard(new Card("K", "SPADES"));
        game.getDealer().getHand().addCard(new Card("Q", "CLUBS"));
        String result = game.determineWinner();
        assertEquals("TIE", result);
    }
}