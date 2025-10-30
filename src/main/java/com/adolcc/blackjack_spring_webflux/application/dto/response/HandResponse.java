package com.adolcc.blackjack_spring_webflux.application.dto.response;

import com.adolcc.blackjack_spring_webflux.domain.model.Hand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HandResponse {
    private List<CardResponse> cards;
    private Integer total;
    private Boolean isBust;
    private Boolean isBlackjack;

    public static HandResponse fromHand(Hand hand) {
        return HandResponse.builder()
                .cards(hand.getCards().stream()
                        .map(CardResponse::fromCard)
                        .collect(Collectors.toList()))
                .total(hand.getTotal())
                .isBust(hand.isBust())
                .isBlackjack(hand.isBlackjack())
                .build();
    }
}