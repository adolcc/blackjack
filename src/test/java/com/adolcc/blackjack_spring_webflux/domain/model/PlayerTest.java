package com.adolcc.blackjack_spring_webflux.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void shouldCreatePlayerWithName() {
        String playerName = "Jordan";
        Player player = new Player(playerName);
        assertNotNull(player);
        assertEquals(playerName, player.getName());
    }
}
