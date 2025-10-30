package com.adolcc.blackjack_spring_webflux.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {
    private String playerId;
    private String playerName;
    private double balance;
}