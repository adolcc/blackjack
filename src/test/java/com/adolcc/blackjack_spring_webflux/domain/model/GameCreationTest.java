package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameCreationTest {
    @Test
    void shouldCreateGameWithPlayerAndDealer() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        assertNotNull(game);
        assertNotNull(game.getPlayer());
        assertNotNull(game.getDealer());
    }

    @Test
    void shouldStartInBettingState() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        assertEquals(GameState.BETTING, game.getState());
    }
}