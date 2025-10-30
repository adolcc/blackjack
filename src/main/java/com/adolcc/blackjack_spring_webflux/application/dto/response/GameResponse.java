package com.adolcc.blackjack_spring_webflux.application.dto.response;

import com.adolcc.blackjack_spring_webflux.domain.model.Game;
import com.adolcc.blackjack_spring_webflux.domain.model.GameState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {
    private String gameId;
    private String playerName;
    private Double playerBalance;
    private HandResponse playerHand;
    private HandResponse dealerHand;
    private GameState state;
    private Double betAmount;
    private String winner;

    public static GameResponse fromGame(Game game, String gameId) {
        return GameResponse.builder()
                .gameId(gameId)
                .playerName(game.getPlayer().getName())
                .playerBalance(game.getPlayer().getBalance())
                .playerHand(HandResponse.fromHand(game.getPlayer().getHand()))
                .dealerHand(HandResponse.fromHand(game.getDealer().getHand()))
                .state(game.getState())
                .betAmount(game.getBetAmount())
                .build();
    }

    public static GameResponse fromGameWithWinner(Game game, String gameId, String winner) {
        GameResponse response = fromGame(game, gameId);
        response.setWinner(winner);
        return response;
    }
}