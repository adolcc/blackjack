package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStartTest {

    @Test
    void shouldDealInitialCards() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.start();
        assertEquals(2, game.getPlayer().getHand().getCards().size());
        assertEquals(2, game.getDealer().getHand().getCards().size());
        assertEquals(48, game.getDeck().getRemainingCards());
    }

    @Test
    void shouldMoveToPlayerTurnAfterStart() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.start();
        assertEquals(GameState.PLAYER_TURN, game.getState());
    }
}