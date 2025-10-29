package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    void shouldCreatedCardWithRankAndSuit() {
        String rank = "A";
        String suit = "HEARTS";
        Card card = new Card(rank, suit);
        assertNotNull(card);
        assertEquals("A", card.getRank());
        assertEquals("HEARTS", card.getSuit());
    }

    @Test
    void shouldReturnCorrectValueForNumberCard() {
        Card card = new Card("7", "HEARTS");
        int value = card.getValue();
        assertEquals(7, value);
    }

    @Test
    void shouldReturnTenForFaceCard() {
        Card kingCard = new Card("K", "SPADES");
        int value = kingCard.getValue();
        assertEquals(10, value);
    }

    @Test
    void shouldReturnElevenForAceCard() {
        Card aceCard = new Card("A", "DIAMONDS");
        int value = aceCard.getValue();
        assertEquals(11, value);
    }
}
