package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerActionsTest {

    @Test
    void shouldAllowPlayerToHit() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.start();
        game.playerHit();
        assertEquals(3, game.getPlayer().getHand().getCards().size());
        assertEquals(47, game.getDeck().getRemainingCards());
    }

    @Test
    void shouldDetectPlayerBustAfterHit() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        player.getHand().addCard(new Card("K", "HEARTS"));
        player.getHand().addCard(new Card("Q", "DIAMONDS"));
        player.getHand().addCard(new Card("5", "CLUBS"));
        assertTrue(game.getPlayer().getHand().isBust());
    }

    @Test
    void shouldMoveToFinishedWhenPlayerBusts() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        player.getHand().addCard(new Card("K", "HEARTS"));
        player.getHand().addCard(new Card("Q", "DIAMONDS"));
        game.setState(GameState.PLAYER_TURN);
        game.playerHit();
        if (player.getHand().isBust()) {
            assertEquals(GameState.FINISHED, game.getState());
        }
    }

    @Test
    void shouldMoveToDealerTurnWhenPlayerStands() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        game.start();
        game.playerStand();
        assertEquals(GameState.DEALER_TURN, game.getState());
    }
}