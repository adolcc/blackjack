package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DealerActionsTest {

    @Test
    void shouldDealerHitUntil17OrMore() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.start();
        game.dealerPlay();
        int dealerTotal = game.getDealer().getHand().getTotal();
        assertTrue(dealerTotal >= 17 || game.getDealer().getHand().isBust());
    }

    @Test
    void shouldMoveToFinishedAfterDealerPlays() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.start();
        game.playerStand();
        game.dealerPlay();
        assertEquals(GameState.FINISHED, game.getState());
    }
}