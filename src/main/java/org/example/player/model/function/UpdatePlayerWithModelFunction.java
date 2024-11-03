package org.example.player.model.function;

import lombok.SneakyThrows;
import org.example.player.entity.Player;
import org.example.player.model.PlayerEditModel;

import java.io.Serializable;
import java.util.function.BiFunction;
public class UpdatePlayerWithModelFunction implements BiFunction<Player, PlayerEditModel, Player>, Serializable {
    @Override
    @SneakyThrows
    public Player apply(Player entity, PlayerEditModel request) {
        return Player.builder()
                .id(entity.getId())
                .name(request.getName())
                .overall(request.getOverall())
                .role(request.getRole())
                .club(entity.getClub())
                .build();
    }
}
