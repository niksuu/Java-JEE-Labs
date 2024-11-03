package org.example.player.model.function;

import org.example.player.entity.Player;
import org.example.player.model.PlayerEditModel;

import java.io.Serializable;
import java.util.function.Function;
public class PlayerToEditModelFunction  implements Function<Player, PlayerEditModel>, Serializable {
    @Override
    public PlayerEditModel apply(Player entity) {
        return PlayerEditModel.builder()
                .name(entity.getName())
                .overall(entity.getOverall())
                .role(entity.getRole())
                .build();
    }
}
