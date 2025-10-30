package com.adolcc.blackjack_spring_webflux.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayRequest {
    @NotBlank(message = "Action is required")
    private String action;

    @PositiveOrZero(message = "Amount must be zero or positive")
    private Double amount;
}