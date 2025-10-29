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