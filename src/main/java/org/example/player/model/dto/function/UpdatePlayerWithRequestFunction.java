package org.example.player.model.dto.function;

import org.example.player.entity.Player;
import org.example.player.model.dto.PatchPlayerRequest;

import java.util.function.BiFunction;

public class UpdatePlayerWithRequestFunction implements BiFunction<Player, PatchPlayerRequest, Player> {
    @Override
    public Player apply(Player player, PatchPlayerRequest patchPlayerRequest) {
        return Player.builder()
                .id(player.getId())
                .name(patchPlayerRequest.getName())
                .overall(patchPlayerRequest.getOverall())
                .role(patchPlayerRequest.getRole())
                .user(player.getUser())
                .club(player.getClub())
                .build();
    }
}
