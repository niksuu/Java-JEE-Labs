package org.example.player.model.dto.function;

import org.example.player.entity.Player;
import org.example.player.model.dto.GetPlayerResponse;

import java.util.function.Function;

public class PlayerToResponseFunction implements Function<Player, GetPlayerResponse> {
    @Override
    public GetPlayerResponse apply(Player entity) {
        return GetPlayerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .overall(entity.getOverall())
                .role(entity.getRole())
                .user(entity.getUser().toString())
                .club(entity.getClub().toString())
                .build();
    }
}