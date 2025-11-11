package com.adolcc.blackjack_spring_webflux.infrastructure.input.rest.controller;

import com.adolcc.blackjack_spring_webflux.application.dto.response.RankingResponse;
import com.adolcc.blackjack_spring_webflux.application.service.RankingService;
import com.adolcc.blackjack_spring_webflux.infrastructure.input.rest.GameController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RankingControllerTest {

    @Mock
    private RankingService rankingService;

    @InjectMocks
    private GameController gameController;

    public RankingControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetRankingSuccessfully() {
        RankingResponse ranking1 = new RankingResponse("1", "Alice", 10, 1000.0);
        RankingResponse ranking2 = new RankingResponse("2", "Bob", 8, 800.0);

        when(rankingService.getRanking()).thenReturn(Flux.fromIterable(List.of(ranking1, ranking2)));

        List<RankingResponse> rankingList = gameController.getRanking().collectList().block();

        assertNotNull(rankingList);
        assertEquals(2, rankingList.size());
        assertEquals("Alice", rankingList.get(0).getPlayerName());
        assertEquals("Bob", rankingList.get(1).getPlayerName());
    }
}