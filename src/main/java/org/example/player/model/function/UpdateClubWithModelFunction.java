package org.example.player.model.function;

import org.example.club.Club;
import org.example.player.model.ClubEditModel;

import java.io.Serializable;
import java.util.function.BiFunction;

public class UpdateClubWithModelFunction implements BiFunction<Club, ClubEditModel, Club>, Serializable {
    @Override
    public Club apply(Club entity, ClubEditModel request) {
        return Club.builder()
                .name(request.getName())
                .budget(request.getBudget())
                .description(request.getDescription())
                .id(request.getId())
                .build();
    }
}
