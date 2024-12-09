package org.example.player.model.function;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.SneakyThrows;
import org.example.player.entity.Player;
import org.example.player.model.PlayerEditModel;
import org.example.user.entity.User;
import java.io.Serializable;
import java.util.UUID;
import java.util.function.BiFunction;
public class UpdatePlayerWithModelFunction implements BiFunction<Player, PlayerEditModel, Player>, Serializable {
    @Override
    @SneakyThrows
    public Player apply(Player entity, PlayerEditModel model) {
        return Player.builder()
                .id(entity.getId())
                .name(model.getName())
                .overall(model.getOverall())
                .role(model.getRole())
                .version(model.getVersion())
                .creationDateTime(entity.getCreationDateTime())
                .updateDateTime(entity.getCreationDateTime())
                .club(entity.getClub())
                .user(User.builder()
                        .id(model.getUser().getId())
                        .build())
                .build();
    }
}