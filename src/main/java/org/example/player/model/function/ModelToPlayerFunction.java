package org.example.player.model.function;
import lombok.SneakyThrows;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.model.ClubCreateModel;
import org.example.player.model.PlayerCreateModel;

import java.io.Serializable;
import java.util.function.Function;
public class ModelToPlayerFunction implements Function<PlayerCreateModel, Player>, Serializable {
    @Override
    @SneakyThrows
    public Player apply(PlayerCreateModel entity) {
        return Player.builder()
                .id(entity.getId())
                .name(entity.getName())
                .role(entity.getRole())
                .overall(entity.getOverall())
                .club(Club.builder()
                        .id(entity.getClub().getId())
                        .build())
                .build();
    }
}