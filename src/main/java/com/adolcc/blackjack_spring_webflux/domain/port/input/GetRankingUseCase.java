package com.adolcc.blackjack_spring_webflux.domain.port.input;

import com.adolcc.blackjack_spring_webflux.application.dto.response.RankingResponse;
import reactor.core.publisher.Flux;

public interface GetRankingUseCase {
    Flux<RankingResponse> getRanking();
}
