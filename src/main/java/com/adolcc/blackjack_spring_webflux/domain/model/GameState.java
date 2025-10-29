package com.adolcc.blackjack_spring_webflux.domain.model;

public enum GameState {
    BETTING,
    PLAYER_TURN,
    DEALER_TURN,
    FINISHED
}