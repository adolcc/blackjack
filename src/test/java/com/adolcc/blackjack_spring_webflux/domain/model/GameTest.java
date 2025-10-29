package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldCreateGameWithPlayerAndDealer() {
        Player player = new Player("John", 1000.0);
        Game game = new Game(player);
        assertNotNull(game);
        assertNotNull(game.getPlayer());
        assertNotNull(game.getDealer());
    }

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
}