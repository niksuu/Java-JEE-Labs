package org.example.player.model.dto.function;

import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.model.dto.PutPlayerRequest;
import org.example.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToPlayerFunction2Params implements BiFunction<UUID, PutPlayerRequest, Player> {
    @Override
    public Player apply(UUID uuid, PutPlayerRequest request) {
        return Player.builder()
                .id(uuid)
                .overall(request.getOverall())
                .name(request.getName())
                .role(request.getRole())
                .club(Club.builder().id(request.getClub()).build())
                .user(User.builder().id(request.getUser()).build())
                .build();
    }
}
