package org.example.player.model.function;

import org.example.player.entity.Player;
import org.example.player.model.PlayerEditModel;
import org.example.user.model.UserModel;
import org.example.user.model.function.UserToModelFunction;
import java.io.Serializable;
import java.util.function.Function;
public class PlayerToEditModelFunction implements Function<Player, PlayerEditModel>, Serializable {
    private final UserToModelFunction userToModelFunction;

    public PlayerToEditModelFunction(UserToModelFunction userToModelFunction) {
        this.userToModelFunction = userToModelFunction;
    }

    @Override
    public PlayerEditModel apply(Player player) {
        return PlayerEditModel.builder()
                .role(player.getRole())
                .name(player.getName())
                .overall(player.getOverall())
                .user(userToModelFunction.apply(player.getUser()))
                .version(player.getVersion())
                .build();
    }
}