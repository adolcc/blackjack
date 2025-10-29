package com.adolcc.blackjack_spring_webflux.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Deck {
    private List<Card> cards;

    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private static final String[] SUITS = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"};

    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public int getRemainingCards() {
        return cards.size();
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards remaining in deck");
        }
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}