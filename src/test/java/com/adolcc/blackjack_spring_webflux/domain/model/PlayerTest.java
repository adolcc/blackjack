package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void shouldCreatePlayerWithInitialBalance() {
        String playerName = "Jordan";
        double initialBalance = 1000.0;
        Player player = new Player(playerName, initialBalance);
        assertNotNull(player);
        assertEquals("Jordan", player.getName());
        assertEquals(1000.0, player.getBalance());
    }

    @Test
    void shouldHaveEmptyHandWhenCreated() {
        String playerName = "John";
        double initialBalance = 1000.0;
        Player player = new Player(playerName, initialBalance);
        assertNotNull(player.getHand());
        assertEquals(0, player.getHand().getTotal());
    }
    @Test
    void shouldDeductBalanceSuccessfully() {
        Player player = new Player("John", 1000.0);
        player.deductBalance(200.0);
        assertEquals(800.0, player.getBalance());
    }

    @Test
    void shouldNotAllowDeductMoreThanBalance() {
        Player player = new Player("John", 1000.0);
        assertThrows(IllegalArgumentException.class, () -> {
            player.deductBalance(1500.0);
        });
    }

    @Test
    void shouldNotAllowDeductNegativeAmount() {
        Player player = new Player("John", 1000.0);
        assertThrows(IllegalArgumentException.class, () -> {
            player.deductBalance(-50.0);
        });
    }
}
