package com.adolcc.blackjack_spring_webflux.application.dto.response;

import com.adolcc.blackjack_spring_webflux.domain.model.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
    private String rank;
    private String suit;
    private Integer value;

    public static CardResponse fromCard(Card card) {
        return CardResponse.builder()
                .rank(card.getRank())
                .suit(card.getSuit())
                .value(card.getValue())
                .build();
    }
}