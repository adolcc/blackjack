package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BettingTest {

    @Test
    void shouldPlaceBetSuccessfully() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.placeBet(100.0);
        assertEquals(100.0, game.getBetAmount());
        assertEquals(900.0, player.getBalance());
    }

    @Test
    void shouldNotAllowBetMoreThanBalance() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        assertThrows(IllegalArgumentException.class, () -> {
            game.placeBet(1500.0);
        });
    }

    @Test
    void shouldNotAllowNegativeBet() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        assertThrows(IllegalArgumentException.class, () -> {
            game.placeBet(-50.0);
        });
    }

    @Test
    void shouldNotAllowZeroBet() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        assertThrows(IllegalArgumentException.class, () -> {
            game.placeBet(0.0);
        });
    }
}