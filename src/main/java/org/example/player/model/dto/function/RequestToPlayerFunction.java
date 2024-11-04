package org.example.player.model.dto.function;

import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.model.dto.PutPlayerRequest;
import org.example.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RequestToPlayerFunction implements BiFunction<UUID,PutPlayerRequest, Player> {
    @Override
    public Player apply(UUID id,PutPlayerRequest putPlayerRequest) {
        return Player.builder()
                .id(id)
                .role(putPlayerRequest.getRole())
                .name(putPlayerRequest.getName())
                .overall(putPlayerRequest.getOverall())
                .club(Club.builder()
                        .id(putPlayerRequest.getClub()).build())
                .user(User.builder()
                        .id(putPlayerRequest.getUser())
                        .build())
                .build();
    }
}
