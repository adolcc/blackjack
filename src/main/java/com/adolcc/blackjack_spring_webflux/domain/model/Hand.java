package com.adolcc.blackjack_spring_webflux.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Hand {
    private List<Card> cards = new ArrayList<>();

    public int getTotal() {
        int total = 0;
        int aceCount = 0;

        for (Card card : cards) {
            int value = card.getValue();
            total += value;
            if (card.getRank().equals("A")) {
                aceCount++;
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public boolean isBust() {
        return getTotal() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getTotal() == 21;
    }
}
