package com.adolcc.blackjack_spring_webflux.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameRequest {

    @NotBlank(message = "Player name is required")
    private String playerName;

    @Positive(message = "Initial balance must be positive")
    private Double initialBalance;
}