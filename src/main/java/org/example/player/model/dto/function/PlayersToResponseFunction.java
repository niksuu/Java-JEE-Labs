package org.example.player.model.dto.function;

import org.example.player.entity.Player;
import org.example.player.model.dto.GetPlayersResponse;

import java.util.List;
import java.util.function.Function;

public class PlayersToResponseFunction implements Function<List<Player>, GetPlayersResponse> {
    @Override
    public GetPlayersResponse apply(List<Player> entities) {
        return GetPlayersResponse.builder()
                .players(entities.stream()
                        .map(
                                player -> GetPlayersResponse.Player.builder()
                                        .id(player.getId())
                                        .role(player.getRole())
                                        .name(player.getName())
                                        .overall(player.getOverall())
                                        .build())
                        .toList())
                .build();
    }
}