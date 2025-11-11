package com.adolcc.blackjack_spring_webflux.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingResponse {
    private String playerId;
    private String playerName;
    private int gamesPlayed;
    private double balance;
}