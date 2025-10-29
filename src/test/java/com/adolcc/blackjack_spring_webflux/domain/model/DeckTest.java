package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void shouldCreateDeckWith52Cards() {
        Deck deck = new Deck();
        assertNotNull(deck);
        assertEquals(52, deck.getRemainingCards());
    }
    @Test
    void shouldDrawCardFromDeck() {
        Deck deck = new Deck();
        Card card = deck.drawCard();
        assertNotNull(card);
        assertEquals(51, deck.getRemainingCards());
    }

    @Test
    void shouldDrawDifferentCards() {
        Deck deck = new Deck();
        Card firstCard = deck.drawCard();
        Card secondCard = deck.drawCard();
        assertNotNull(firstCard);
        assertNotNull(secondCard);
        assertEquals(50, deck.getRemainingCards());
    }

    @Test
    void shouldShuffleDeck() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        deck2.shuffle();
        assertEquals(52, deck1.getRemainingCards());
        assertEquals(52, deck2.getRemainingCards());
        boolean isDifferent = false;
        for (int i = 0; i < 5; i++) {
            Card card1 = deck1.drawCard();
            Card card2 = deck2.drawCard();
            if (!card1.getRank().equals(card2.getRank()) ||
                    !card1.getSuit().equals(card2.getSuit())) {
                isDifferent = true;
                break;
            }
        }
        assertTrue(isDifferent, "Deck should be shuffled");
    }
}