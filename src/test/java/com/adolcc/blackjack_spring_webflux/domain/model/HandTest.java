package com.adolcc.blackjack_spring_webflux.domain.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    @Test
    void shouldCreateEmptyHand() {
        Hand hand = new Hand();
        assertNotNull(hand);
        assertEquals(0, hand.getTotal());
    }

    @Test
    void shouldAddCardToHand(){
        Hand hand = new Hand();
        Card card = new Card("K", "HEARTS");
        hand.addCard(card);
        assertEquals(1, hand.getCards().size());
        assertEquals(10, hand.getTotal());
    }

    @Test
    void shouldCalculateTotalWithMultipleCards(){
        Hand hand = new Hand();
        hand.addCard(new Card("7", "HEARTS"));
        hand.addCard(new Card("9", "DIAMONDS"));
        assertEquals(2, hand.getCards().size());
        assertEquals(16, hand.getTotal());
    }

    @Test
    void shouldDetectBust() {
        Hand hand = new Hand();
        hand.addCard(new Card("K", "HEARTS"));
        hand.addCard(new Card("Q", "DIAMONDS"));
        hand.addCard(new Card("5", "CLUBS"));
        boolean isBust = hand.isBust();
        assertTrue(isBust);
        assertEquals(25, hand.getTotal());
    }

    @Test
    void shouldDetectBlackjack() {
        Hand hand = new Hand();
        hand.addCard(new Card("A", "HEARTS"));
        hand.addCard(new Card("K", "DIAMONDS"));
        boolean isBlackjack = hand.isBlackjack();
        assertTrue(isBlackjack);
        assertEquals(21, hand.getTotal());
    }
}
