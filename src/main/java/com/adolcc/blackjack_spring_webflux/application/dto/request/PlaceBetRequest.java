package com.adolcc.blackjack_spring_webflux.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceBetRequest {

    @NotNull(message = "Bet amount is required")
    @Positive(message = "Bet amount must be positive")
    private Double amount;
}