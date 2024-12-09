package org.example.player.model.dto.function;

import org.example.component.TriFunction;
import org.example.club.Club;
import org.example.player.entity.Player;
import org.example.player.model.dto.PutPlayerRequest;
import org.example.user.entity.User;

import java.util.UUID;

public class RequestToPlayerFunction implements TriFunction<UUID, UUID, PutPlayerRequest, Player> {
    @Override
    public Player apply(UUID clubId, UUID playerId, PutPlayerRequest request) {
        return Player.builder()
                .id(playerId)
                .role(request.getRole())
                .name(request.getName())
                .overall(request.getOverall())
                .club(Club.builder()
                        .id(clubId)
                        .build())
                .user(User.builder()
                        .id(request.getUser())
                        .build())
                .build();
    }
}
