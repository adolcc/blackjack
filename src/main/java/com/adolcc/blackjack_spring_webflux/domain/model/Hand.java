package com.adolcc.blackjack_spring_webflux.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public int getTotal() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
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
