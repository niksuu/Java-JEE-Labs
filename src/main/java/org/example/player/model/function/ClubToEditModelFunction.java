package org.example.player.model.function;

import org.example.club.Club;
import org.example.player.model.ClubEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class ClubToEditModelFunction implements Function<Club, ClubEditModel>, Serializable {
    @Override
    public ClubEditModel apply(Club club) {
        return ClubEditModel.builder()
                .name(club.getName())
                .description(club.getDescription())
                .budget(club.getBudget())
                .id(club.getId())
                .build();
    }
}
