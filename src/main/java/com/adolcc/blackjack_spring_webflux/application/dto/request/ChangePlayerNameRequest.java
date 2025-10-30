package com.adolcc.blackjack_spring_webflux.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePlayerNameRequest {
    @NotBlank(message = "New player name is required")
    private String newName;
}