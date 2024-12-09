package org.example.player.model.function;

import org.example.player.entity.Player;
import org.example.player.model.PlayersModel;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class PlayersToModelFunction implements Function<List<Player>, PlayersModel>, Serializable {
    @Override
    public PlayersModel apply(List<Player> entities) {
        return PlayersModel.builder()
                .players(entities.stream().map(player ->
                                PlayersModel.Player.builder()
                                        .id(player.getId())
                                        .name(player.getName())
                                        .overall(player.getOverall())
                                        .role(player.getRole())
                                        .version(player.getVersion())
                                        .creationDateTime(player.getCreationDateTime())
                                        .updateDateTime(player.getUpdateDateTime())
                                        .build()
                        )
                        .toList())
                .build();
    }
}