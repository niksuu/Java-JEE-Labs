package org.example.player.model.function;
import org.example.player.entity.Player;
import org.example.player.model.PlayerModel;
import java.io.Serializable;
import java.util.function.Function;
public class PlayerToModelFunction implements Function<Player, PlayerModel>, Serializable {
    @Override
    public PlayerModel apply(Player entity) {
        return PlayerModel.builder()
                .id(entity.getId())
                .role(entity.getRole())
                .name(entity.getName())
                .overall(entity.getOverall())
                .club(entity.getClub().getName())
                .user(entity.getUser().getUsername())
                .build();
    }
}