package org.example.player.model.function;

import org.example.player.entity.Club;
import org.example.player.model.ClubModel;

import java.io.Serializable;
import java.util.function.Function;

public class ClubToModelFunction implements Function<Club, ClubModel>, Serializable {

    @Override
    public ClubModel apply(Club club) {
        return ClubModel.builder()
                .id(club.getId())
                .name(club.getName())
                .description(club.getDescription())
                .budget(club.getBudget())
                .players(club.getPlayers().stream()
                        .map(player -> ClubModel.Player.builder()
                                .id(player.getId())
                                .name(player.getName())
                                .role(player.getRole())
                                .overall(player.getOverall())
                                .build())
                        .toList())
                .build();
    }

}
